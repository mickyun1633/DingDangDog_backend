document.addEventListener("DOMContentLoaded", () => {
  currentArchivePage = 0;
  renderArchiveCards();
  renderCareList();

  const prevBtn = document.getElementById("archive-prev-btn");
  const nextBtn = document.getElementById("archive-next-btn");

  if (prevBtn) {
    prevBtn.addEventListener("click", () => {
      if (currentArchivePage > 0) {
        currentArchivePage--;
        renderArchiveCards();
      }
    });
  }

  if (nextBtn) {
    nextBtn.addEventListener("click", () => {
      const maxPage = Math.ceil(mainArchiveData.length / 4) - 1;
      if (currentArchivePage < maxPage) {
        currentArchivePage++;
        renderArchiveCards();
      }
    });
  }
});

let currentArchivePage = 0;
const archivePageSize = 4;

function renderArchiveCards() {
  const cardList = document.getElementById("main-card-list");
  if (!cardList || !Array.isArray(mainArchiveData)) return;

  const start = currentArchivePage * archivePageSize;
  const end = start + archivePageSize;
  const visibleArchives = mainArchiveData.slice(start, end);

  cardList.innerHTML = "";

  visibleArchives.forEach((dog) => {
    const card = document.createElement("div");
    card.className = "dog-card";

    const imagePath = dog.archiveImgPath && dog.archiveImgPath.trim() !== ""
      ? `${contextPath}${dog.archiveImgPath}`
      : `${contextPath}/assets/img/1.png`;

    card.innerHTML = `
      <a href="${contextPath}/archive/detail.ar?dogNumber=${dog.dogNumber}">
        <img src="${imagePath}" alt="${escapeHtml(dog.dogName || "강아지")}">
      </a>
    `;

    cardList.appendChild(card);
  });

  updateArchiveButtons();
}

function updateArchiveButtons() {
  const prevBtn = document.getElementById("archive-prev-btn");
  const nextBtn = document.getElementById("archive-next-btn");

  if (!Array.isArray(mainArchiveData)) return;

  const maxPage = Math.ceil(mainArchiveData.length / archivePageSize) - 1;

  if (prevBtn) {
    prevBtn.disabled = currentArchivePage === 0;
  }

  if (nextBtn) {
    nextBtn.disabled = currentArchivePage >= maxPage;
  }
}

function renderCareList() {
  const careList = document.getElementById("care-list");
  if (!careList || !Array.isArray(mainCareData)) return;

  const visibleCares = mainCareData.slice(0, 5);
  careList.innerHTML = "";

  visibleCares.forEach((care) => {
    const li = document.createElement("li");
    li.className = "care-item";

    li.innerHTML = `
      <a href="${contextPath}/care/detail.ca?careNumber=${care.careNumber}" class="care-link">
        <span class="care-title">${escapeHtml(care.careTitle || "")}</span>
        <span class="care-date">${formatDate(care.careDate)}</span>
      </a>
    `;

    careList.appendChild(li);
  });
}

function formatDate(dateValue) {
  if (!dateValue) return "";

  const date = new Date(dateValue);
  if (Number.isNaN(date.getTime())) {
    return String(dateValue).length >= 10
      ? String(dateValue).substring(2, 10)
      : String(dateValue);
  }

  const yy = String(date.getFullYear()).slice(2);
  const mm = String(date.getMonth() + 1).padStart(2, "0");
  const dd = String(date.getDate()).padStart(2, "0");

  return `${yy}-${mm}-${dd}`;
}

function escapeHtml(value) {
  return String(value)
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#39;");
}









