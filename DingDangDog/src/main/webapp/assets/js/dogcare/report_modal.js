document.addEventListener("DOMContentLoaded", function () {
    const applyBtn = document.getElementById("applyStatusBtn");
    const modal = document.getElementById("applyModal");
    const closeBtn = document.getElementById("modalClose");

    // 신청현황 클릭 시 모달 열기
    if (applyBtn && modal) {
        applyBtn.addEventListener("click", function () {
            modal.style.display = "block";
        });
    }

    // 닫기 버튼 클릭 시 모달 닫기
    if (closeBtn && modal) {
        closeBtn.addEventListener("click", function () {
            modal.style.display = "none";
        });
    }

    // 배경 클릭 시 모달 닫기
    window.addEventListener("click", function (e) {
        if (modal && e.target === modal) {
            modal.style.display = "none";
        }
    });

    // 기타 선택 시 입력창 활성화
    const reportReasons = document.querySelectorAll(".report-reason");

    reportReasons.forEach(function (reasonBox) {
        const radios = reasonBox.querySelectorAll("input[type='radio']");
        const etcInput = reasonBox.querySelector(".etc-input");

        if (!etcInput) return;

        radios.forEach(function (radio) {
            radio.addEventListener("change", function () {
                if (radio.value === "기타" && radio.checked) {
                    etcInput.disabled = false;
                    etcInput.focus();
                } else if (radio.checked) {
                    etcInput.disabled = true;
                    etcInput.value = "";
                }
            });
        });
    });

    // 신고 폼 제출 검증
    const reportForms = document.querySelectorAll(".report-form");

    reportForms.forEach(function (form) {
        form.addEventListener("submit", function (e) {
            const checkedRadio = form.querySelector("input[type='radio']:checked");
            const etcInput = form.querySelector(".etc-input");

            if (!checkedRadio) {
                alert("신고 사유를 선택해주세요.");
                e.preventDefault();
                return;
            }

            if (checkedRadio.value === "기타") {
                if (!etcInput || etcInput.value.trim() === "") {
                    alert("기타 사유를 입력해주세요.");
                    if (etcInput) etcInput.focus();
                    e.preventDefault();
                    return;
                }
            }

            if (!confirm("신고하시겠습니까?")) {
                e.preventDefault();
            }
        });
    });
});