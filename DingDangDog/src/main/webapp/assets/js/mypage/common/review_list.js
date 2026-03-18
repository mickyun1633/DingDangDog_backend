document.addEventListener("DOMContentLoaded", function () {
  const rows = Array.from(document.querySelectorAll(".review-table-row"));
  const rowsPerPage = 10;
  const pageCount = 5;

  let currentPage = 1;
  let filteredRows = [...rows];

  const pageList = document.querySelector(".page-list");
  const prevBtn = document.querySelector(".prev-btn");
  const nextBtn = document.querySelector(".next-btn");
  const emptyBox = document.querySelector(".empty-box");
  const contextPath = window.location.pathname.substring(
    0,
    window.location.pathname.indexOf("/", 1)
  );

  function renderPage(page) {
    rows.forEach((row) => {
      row.style.display = "none";
    });

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    filteredRows.slice(start, end).forEach((row) => {
      row.style.display = "flex";
    });
  }

  function createPagination() {
    const totalPages = Math.ceil(filteredRows.length / rowsPerPage);

    const oldBtns = document.querySelectorAll(".page-item");
    oldBtns.forEach((btn) => btn.parentElement.remove());

    if (totalPages === 0) {
      if (prevBtn) prevBtn.disabled = true;
      if (nextBtn) nextBtn.disabled = true;
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
        renderPage(currentPage);
        createPagination();
      });

      li.appendChild(btn);
      pageList.insertBefore(li, nextBtn.parentElement);
    }

    prevBtn.disabled = currentPage === 1;
    nextBtn.disabled = currentPage === totalPages;
  }

  if (prevBtn) {
    prevBtn.addEventListener("click", () => {
      if (currentPage > 1) {
        currentPage--;
        renderPage(currentPage);
        createPagination();
      }
    });
  }

  if (nextBtn) {
    nextBtn.addEventListener("click", () => {
      const totalPages = Math.ceil(filteredRows.length / rowsPerPage);
      if (currentPage < totalPages) {
        currentPage++;
        renderPage(currentPage);
        createPagination();
      }
    });
  }

  rows.forEach((row) => {
    row.style.cursor = "pointer";
    row.addEventListener("click", () => {
      const logNumber = row.dataset.logNumber;
      if (logNumber) {
        location.href = `${contextPath}/log/detail.lo?logNumber=${logNumber}`;
      }
    });
  });

  if (rows.length > 0) {
    renderPage(currentPage);
    createPagination();
  } else {
    if (emptyBox) {
      emptyBox.style.display = "block";
    }
    if (prevBtn) prevBtn.disabled = true;
    if (nextBtn) nextBtn.disabled = true;
  }
});