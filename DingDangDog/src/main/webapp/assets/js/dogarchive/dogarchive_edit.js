// 1. 필요한 요소 가져오기
const fileInput = document.getElementById('editImage');
const previewImg = document.getElementById('previewImage');
const btnEditSave = document.getElementById('btnEditSave');
const btnCancle = document.getElementById('btnCancle');

// [이미지 미리보기] 사진 선택 시 즉시 변경
if (fileInput && previewImg) {
    fileInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file && file.type.match('image.*')) {
            const reader = new FileReader();
            reader.onload = (e) => previewImg.src = e.target.result;
            reader.readAsDataURL(file);
        }
    });
}

// [취소 버튼] 팀원분 로직 반영
if (btnCancle) {
    btnCancle.addEventListener("click", function () {
        alert("수정이 취소되었습니다.");
        // 상세 페이지로 돌아가기 (dogNumber는 폼 안에 hidden값 사용)
        const dogNumber = document.querySelector('input[name="dogNumber"]').value;
        location.href = "/DingDangDog/archive/read.ar?dogNumber=" + dogNumber;
    });
}

// [수정 완료 버튼] 실제 데이터 전송 + 팀원분 알림 로직 합체
if (btnEditSave) {
    btnEditSave.addEventListener("click", function () {
        const form = document.getElementById('editForm');
        const formData = new FormData(form);

        // 실제 서버로 데이터 전송 (fetch)
		fetch('/DingDangDog/archive/updateOk.ar', {
		    method: 'POST',
		    body: formData
		})
        .then(response => response.json())
        .then(data => {
            if (data.status === "success") {
                alert("수정되었습니다."); // 팀원분 알림 문구
                // 수정된 게시물 상세 페이지로 이동
                location.href = "/DingDangDog/archive/read.ar?dogNumber=" + data.dogNumber;
            } else {
                alert("수정에 실패했습니다. 내용을 확인해주세요.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("서버 통신 중 오류가 발생했습니다.");
        });
    });
}