const getContextPath = () => {
    const path = window.location.pathname;
    const ctx = path.substring(0, path.indexOf('/', 1));
    if (ctx === "/archive" || ctx === "/app" || ctx === "/assets") return "";
    return ctx;
};

const cp = getContextPath();

// 1. 요소 가져오기
const imageUpload = document.getElementById("imageUpload");
const thumbnailPreview = document.getElementById("thumbnailPreview");
const imgPlaceholder = document.querySelector(".img-placeholder");
const archiveContentEditor = document.getElementById("archiveContentEditor");
const btnSubmit = document.getElementById("btnEditSave");

const dogName = document.getElementById("dogName");
const dogGenderInput = document.getElementById("dogGender");
const genderBtns = document.querySelectorAll(".gender-btn");

// 점수 요소들
const scoreActivity = document.getElementById("scoreActivity");
const scoreSocial = document.getElementById("scoreSocial");
const scoreIndependence = document.getElementById("scoreIndependence");
const scoreBark = document.getElementById("scoreBark");
const scoreGrooming = document.getElementById("scoreGrooming");

let uploadedImages = [];

// 2. 이벤트 연결
if (imageUpload) imageUpload.addEventListener("change", handleImageUpload);
if (btnSubmit) btnSubmit.addEventListener("click", handleSubmit);

// --- 성별 버튼 클릭 이벤트 추가 ---
genderBtns.forEach(btn => {
    btn.addEventListener("click", function() {

        genderBtns.forEach(b => b.classList.remove("active"));
        this.classList.add("active");
        

        dogGenderInput.value = this.getAttribute("data-value");
        console.log("선택된 성별:", dogGenderInput.value);
    });
});

// 3. 이미지 업로드 로직
function handleImageUpload(event) {
    const file = event.target.files[0];
    if (!file) return;

    if (!file.type.startsWith("image/")) {
        alert("이미지 파일만 선택 가능합니다.");
        return;
    }

    const reader = new FileReader();
    reader.onload = function(e) {
        uploadedImages = [{ id: Date.now(), src: e.target.result }];
        if (thumbnailPreview) {
            thumbnailPreview.src = e.target.result;
            thumbnailPreview.style.display = "block";
        }
        if (imgPlaceholder) imgPlaceholder.style.display = "none";
    };
    reader.readAsDataURL(file);
}

// 4. 데이터 전송
function handleSubmit() {
    // 필수 입력 검사
    if (!dogName.value.trim()) { 
        alert("이름을 입력해주세요."); 
        dogName.focus(); 
        return; 
    }
    
    if (!dogGenderInput.value) {
        alert("성별을 선택해주세요.");
        return;
    }
    
    if (uploadedImages.length === 0) { 
        alert("이미지를 등록해주세요."); 
        return; 
    }

    const form = document.createElement('form');
    form.method = 'POST';
    form.action = cp + '/archive/writeOk.ar'; 

    const params = {
        "dogName": dogName.value,
        "dogBreed": document.getElementById("dogBreed").value || "알수없음",
        "dogGender": dogGenderInput.value,
        "dogAge": document.getElementById("dogAge").value || "0",
        "dogWeight": document.getElementById("dogWeight").value || "0",
        "DogSafeDate": document.getElementById("dogRescueDate").value || "", 
        "dogDetail": archiveContentEditor.innerHTML || "",
        "dogActivity": scoreActivity.value || "0",
        "dogSociality": scoreSocial.value || "0",
        "dogIndependence": scoreIndependence.value || "0",
        "dogBarking": scoreBark.value || "0",
        "dogGrooming": scoreGrooming.value || "0",
        "archiveImgName": "dog_main.png",
        "archiveImgPath": uploadedImages[0].src 
    };

    for (const key in params) {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = key;
        input.value = params[key];
        form.appendChild(input);
    }

    document.body.appendChild(form);
    form.submit();
}