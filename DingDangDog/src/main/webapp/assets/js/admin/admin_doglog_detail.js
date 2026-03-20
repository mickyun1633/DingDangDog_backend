document.addEventListener("DOMContentLoaded", function () {
  const detailTitle = document.getElementById("detailTitle");
  const detailWriter = document.getElementById("detailWriter");
  const detailDate = document.getElementById("detailDate");
  const detailImageBox = document.getElementById("detailImageBox");
  const detailPost = document.getElementById("detailPost");
  const commentListBox = document.getElementById("commentListBox");
  const removeBtn = document.getElementById("btn-remove-detail");

  renderDetail();
  renderComments();
  bindEvents();

  function renderDetail() {
    detailTitle.textContent = logDetailData.title || "제목 없음";
    detailWriter.textContent = logDetailData.writer || "작성자 없음";
    detailDate.textContent = formatDate(logDetailData.date);
    detailPost.textContent = logDetailData.post || "";

    if (logDetailData.imagePath && logDetailData.imagePath.trim() !== "") {
      detailImageBox.innerHTML = `
        <img src="${contextPath}${logDetailData.imagePath}" alt="대표 이미지" />
      `;
    } else {
      detailImageBox.innerHTML = `
        <div class="detail-empty-image">등록된 이미지가 없습니다.</div>
      `;
    }
  }

  function renderComments() {
    commentListBox.innerHTML = "";

    if (!commentData || commentData.length === 0) {
      commentListBox.innerHTML = `
        <div class="detail-comment-empty">등록된 댓글이 없습니다.</div>
      `;
      return;
    }

    commentData.forEach((comment) => {
      const commentWrap = document.createElement("div");
      commentWrap.classList.add("detail-comment-container");

      commentWrap.innerHTML = `
        <div class="detail-comment-info">
          <div class="comment-info-name">${comment.writer || "익명"}</div>
          <div class="comment-info-date">${formatDate(comment.date)}</div>
        </div>
        <div class="detail-comment-post">
          <div class="comment-post-content">${comment.post || ""}</div>
          <div class="btn-comment-delete btn" data-comment-number="${comment.commentNumber}">댓글 삭제</div>
        </div>
      `;

      commentListBox.appendChild(commentWrap);
    });
  }

  function bindEvents() {
    removeBtn.addEventListener("click", function () {
      const isConfirmed = confirm("이 게시글을 삭제하시겠습니까?");
      if (!isConfirmed) return;

      location.href = `${contextPath}/admin/adminLogDeleteOk.ad?logNumber=${logDetailData.logNumber}`;
    });

    commentListBox.addEventListener("click", function (e) {
      const deleteBtn = e.target.closest(".btn-comment-delete");
      if (!deleteBtn) return;

      const commentNumber = deleteBtn.dataset.commentNumber;
      const isConfirmed = confirm("이 댓글을 삭제하시겠습니까?");
      if (!isConfirmed) return;

      location.href = `${contextPath}/admin/adminLogCommentDeleteOk.ad?commentNumber=${commentNumber}&logNumber=${logDetailData.logNumber}`;
    });
  }

  function formatDate(value) {
    if (!value) return "";

    return String(value)
      .replace("T", " ")
      .replaceAll("-", ".")
      .substring(0, 16);
  }
});