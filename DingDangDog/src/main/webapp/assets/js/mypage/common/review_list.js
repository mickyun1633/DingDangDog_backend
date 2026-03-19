const reviewTableBody = document.getElementById("reviewTableBody");
const pagination = document.getElementById("pagination");

function renderTable(page) {
  const start = (page - 1) * rowsPerPage;
  const end = start + rowsPerPage;
  const currentData = reviewData.slice(start, end);

  reviewTableBody.innerHTML = "";

  if (!currentData.length) {
    reviewTableBody.innerHTML = `
      <div class="empty-box">작성한 멍! 로그가 없습니다.</div>
    `;
    return;
  }

  currentData.forEach((review) => {
    const row = document.createElement("div");
    row.classList.add("review-table-row");
    row.style.cursor = "pointer";

    row.innerHTML = `
      <div class="col-number">${review.id}</div>
      <div class="col-title">${review.title || "제목 없음"}</div>
      <div class="col-date">${review.date || ""}</div>
    `;

    row.addEventListener("click", () => {
      location.href = `${contextPath}/log/detail.lo?logNumber=${review.id}`;
    });

    reviewTableBody.appendChild(row);
  });
}


//페이지네이션 동작 코드
//reviewData 부분만 맞춰서 수정하면 됨
const rowsPerPage = 10; //페이지당 보여줄 정보 개수
let currentPage = 1;
const maxVisiblePages = 5; //페이지네이션 버튼 개수

function renderPagination() {
  const totalPages = Math.ceil(reviewData.length / rowsPerPage);
  pagination.innerHTML = "";

  let startPage = currentPage - Math.floor(maxVisiblePages / 2);
  let endPage = currentPage + Math.floor(maxVisiblePages / 2);

  if (startPage < 1) {
    startPage = 1;
    endPage = Math.min(maxVisiblePages, totalPages);
  }

  if (endPage > totalPages) {
    endPage = totalPages;
    startPage = Math.max(1, totalPages - maxVisiblePages + 1);
  }

  const prevLi = document.createElement("li");
  const prevBtn = document.createElement("button");
  prevBtn.classList.add("prev-btn");
  prevBtn.innerHTML = "<span>&lt;</span>";
  prevBtn.disabled = currentPage === 1;

  prevBtn.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      updatePage();
    }
  });

  prevLi.appendChild(prevBtn);
  pagination.appendChild(prevLi);

  for (let i = startPage; i <= endPage; i++) {
    const li = document.createElement("li");
    const btn = document.createElement("button");

    btn.classList.add("page-item");
    if (i === currentPage) {
      btn.classList.add("current-page");
    }

    btn.textContent = i;

    btn.addEventListener("click", () => {
      currentPage = i;
      updatePage();
    });

    li.appendChild(btn);
    pagination.appendChild(li);
  }

  const nextLi = document.createElement("li");
  const nextBtn = document.createElement("button");
  nextBtn.classList.add("next-btn");
  nextBtn.innerHTML = "<span>&gt;</span>";
  nextBtn.disabled = currentPage === totalPages || totalPages === 0;

  nextBtn.addEventListener("click", () => {
    if (currentPage < totalPages) {
      currentPage++;
      updatePage();
    }
  });

  nextLi.appendChild(nextBtn);
  pagination.appendChild(nextLi);
}

function updatePage() {
  renderTable(currentPage);
  renderPagination();
}

updatePage();