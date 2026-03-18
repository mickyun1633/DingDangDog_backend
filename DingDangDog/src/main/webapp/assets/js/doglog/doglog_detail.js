const detailTitle = document.getElementById("detailTitle");
const detailWriter = document.getElementById("detailWriter");
const detailDate = document.getElementById("detailDate");
const detailMainImg = document.getElementById("detailMainImg");
const detailMainPost = document.getElementById("detailMainPost");

const commentList = document.getElementById("commentList");
const commentWriteContainer = document.getElementById("commentWriteContainer");
const commentWriteInput = document.getElementById("commentWriteInput");
const btnCommentWrite = document.getElementById("btnCommentWrite");

const btnBackToList = document.getElementById("btnBackToList");
const btnDetailEdit = document.getElementById("btnDetailEdit");
const btnDetailDelete = document.getElementById("btnDetailDelete");

const commentForm = document.getElementById("commentForm");
const commentNumberInput = document.getElementById("commentNumber");
const logNumberInput = document.getElementById("logNumber");
const commentCancelWrap = document.getElementById("commentCancelWrap");
const btnCommentCancel = document.getElementById("btnCommentCancel");

/* =========================
   초기 실행
========================= */
bindEvents();

/* =========================
   이벤트 등록
========================= */
function bindEvents() {
  bindCommentWriteForm();
  bindCommentEdit();
  bindPostDelete();
  bindCommentCancelButton();
}

/* =========================
   댓글 작성 / 수정
   - form submit 기준으로 검사
========================= */
function bindCommentWriteForm() {
  if (!commentForm || !commentWriteInput) return;

  commentForm.addEventListener("submit", function (event) {
    const commentText = commentWriteInput.value.trim();

    if (!commentText) {
      event.preventDefault();
      alert("댓글 내용을 입력해주세요.");
      commentWriteInput.focus();
      return;
    }
  });
}

/* =========================
   댓글 수정
   - 수정 버튼 클릭 시 기존 댓글 내용을 입력창에 세팅
   - form action을 editOk로 변경
========================= */
function bindCommentEdit() {
  if (!commentList || !commentForm || !commentWriteInput || !btnCommentWrite || !commentNumberInput) return;

  commentList.addEventListener("click", function (event) {
    const editTarget = event.target.closest('[data-action="edit-comment"]');
    if (!editTarget) return;

    const commentNumber = editTarget.dataset.commentNumber;
    if (!commentNumber) return;

    const commentContainer = editTarget.closest(".detail-comment-container");
    if (!commentContainer) return;

    const commentContent = commentContainer.querySelector(".comment-post-content");
    const commentPost = commentContent ? commentContent.textContent.trim() : "";

    commentWriteInput.value = commentPost;
    commentNumberInput.value = commentNumber;
    commentForm.action = contextPath + "/comment/editOk.lo";
    btnCommentWrite.textContent = "댓글 수정";

    if (commentCancelWrap) {
      commentCancelWrap.style.display = "block";
    }

    commentWriteInput.focus();
  });
}

/* =========================
   댓글 수정 취소
========================= */
function bindCommentCancelButton() {
  if (!btnCommentCancel || !commentForm || !commentWriteInput || !btnCommentWrite || !commentNumberInput) return;

  btnCommentCancel.addEventListener("click", function () {
    resetCommentForm();
  });
}

function resetCommentForm() {
  commentWriteInput.value = "";
  commentNumberInput.value = "";
  commentForm.action = contextPath + "/comment/writeOk.lo";
  btnCommentWrite.textContent = "댓글 작성";

  if (commentCancelWrap) {
    commentCancelWrap.style.display = "none";
  }
}

/* =========================
   게시글 삭제
========================= */
function bindPostDelete() {
  if (!btnDetailDelete) return;

  btnDetailDelete.addEventListener("click", function (event) {
    const isConfirmed = confirm("게시글을 삭제하시겠습니까?");
    if (!isConfirmed) {
      event.preventDefault();
      return;
    }

    const deleteUrl = btnDetailDelete.dataset.deleteUrl;
    if (!deleteUrl) return;

    location.href = deleteUrl;
  });
}

/* =========================
   공통 유틸
========================= */
function escapeHtml(value) {
  return String(value)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#39;");
}