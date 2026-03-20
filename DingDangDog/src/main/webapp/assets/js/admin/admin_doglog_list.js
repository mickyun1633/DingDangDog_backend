document.addEventListener("DOMContentLoaded", function () {
  const rowsPerPage = 15;
  const pageCount = 5;

  let currentPage = 1;
  let filteredData = Array.isArray(reviewData) ? [...reviewData] : [];

  const pageList = document.querySelector(".page-list");
  const prevBtn = document.querySelector(".prev-btn");
  const nextBtn = document.querySelector(".next-btn");
  const searchBtn = document.querySelector(".btn-search");
  const searchInput = document.querySelector(".search-input");
  const searchSelect = document.querySelector(".search-select");
  const reviewTableBody = document.getElementById("reviewTableBody");

  function renderPage(page) {
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const currentData = filteredData.slice(start, end);

    reviewTableBody.innerHTML = "";

    if (currentData.length === 0) {
      reviewTableBody.innerHTML = `<div class="admin-doglog-empty">조회된 게시글이 없습니다.</div>`;
      return;
    }

    currentData.forEach((review) => {
      const row = document.createElement("div");
      row.classList.add("admin-doglog-list-row");
      row.style.cursor = "pointer";

      row.innerHTML = `
        <div class="doglog-number">${review.id}</div>
        <div class="doglog-title">${review.title || "제목 없음"}</div>
        <div class="doglog-id">${review.userId || ""}</div>
        <div class="doglog-nickname">${review.userNickName || ""}</div>
        <div class="doglog-date">${formatDate(review.date)}</div>
      `;

      row.addEventListener("click", () => {
        location.href = `${contextPath}/admin/adminLogDetailOk.ad?logNumber=${review.id}`;
      });

      reviewTableBody.appendChild(row);
    });
  }

  function createPagination() {
    const totalPages = Math.ceil(filteredData.length / rowsPerPage);

    document.querySelectorAll(".page-item").forEach((btn) => {
      btn.parentElement.remove();
    });

    if (totalPages === 0) {
      return;
    }

    const pageGroup = Math.ceil(currentPage / pageCount);
    const startPage = (pageGroup - 1) * pageCount + 1;
    const endPage = Math.min(startPage + pageCount - 1, totalPages);

    for (let i = startPage; i <= endPage; i++) {
      const li = document.createElement("li");
      const btn = document.createElement("button");

      btn.type = "button";
      btn.className = "page-item";
      btn.textContent = i;

      if (i === currentPage) {
        btn.classList.add("current-page");
      }

      btn.addEventListener("click", () => {
        currentPage = i;
        updatePage();
      });

      li.appendChild(btn);
      pageList.insertBefore(li, nextBtn.parentElement);
    }
  }

  function updatePage() {
    renderPage(currentPage);
    createPagination();
  }

  function performSearch() {
    const keyword = searchInput.value.trim();
    const type = searchSelect.value;

    if (!keyword) {
      filteredData = [...reviewData];
    } else {
      filteredData = reviewData.filter((review) => {
        const id = String(review.userId || "");
        const nickname = String(review.userNickName || "");

        if (type === "아이디") return id.includes(keyword);
        if (type === "닉네임") return nickname.includes(keyword);
        return true;
      });
    }

    currentPage = 1;
    updatePage();
  }

  prevBtn.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      updatePage();
    }
  });

  nextBtn.addEventListener("click", () => {
    const totalPages = Math.ceil(filteredData.length / rowsPerPage);

    if (currentPage < totalPages) {
      currentPage++;
      updatePage();
    }
  });

  searchBtn.addEventListener("click", performSearch);

  searchInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      performSearch();
    }
  });

  updatePage();

  function formatDate(value) {
    if (!value) return "";

    return String(value)
      .replace("T", " ")
      .replaceAll("-", ".")
      .substring(0, 16);
  }
});