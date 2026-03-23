document.addEventListener('DOMContentLoaded', function() {
    const imageUpload = document.getElementById('imageUpload');
    const thumbnailPreview = document.getElementById('thumbnailPreview');
    const imgPlaceholder = document.querySelector('.img-placeholder');
    const btnEditSave = document.getElementById('btnEditSave');
    const archiveForm = document.getElementById('archiveForm');
    const editor = document.getElementById('archiveContentEditor');
    const hiddenContent = document.getElementById('dogContentHidden');

    // 1. 이미지 미리보기
    imageUpload.addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                thumbnailPreview.src = event.target.result;
                thumbnailPreview.style.display = 'block';
                if (imgPlaceholder) imgPlaceholder.style.display = 'none';
            };
            reader.readAsDataURL(file);
        }
    });

    // 2. 등록 버튼 클릭 시 전송
    btnEditSave.addEventListener('click', function() {
        // 유효성 검사
        if (!document.getElementById('dogName').value.trim()) {
            alert("강아지 이름을 입력해주세요!");
            return;
        }

        // 에디터 내용 반영
        if (editor && hiddenContent) {
            hiddenContent.value = editor.innerHTML;
        }

        // 폼 전송 (enctype="multipart/form-data"가 설정된 form을 전송)
        archiveForm.submit();
    });
});