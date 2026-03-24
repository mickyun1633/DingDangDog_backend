const saveBtn = document.getElementById("result-save-btn");
const cardWrapper = document.getElementById("card-wrapper");

function createDogCard(dog) {
  const imageHtml = dog.archiveImgPath
    ? `<img src="${contextPath}${dog.archiveImgPath}" alt="${dog.dogName}">`
    : `이미지`;

  return `
    <div class="dogarchive-card">
      <a href="${contextPath}/archive/read.ar?dogNumber=${dog.dogNumber}" class="dogarchive-link">
        <div class="dogarchive-image">${imageHtml}</div>

        <div class="dogarchive-content">
          <div class="dogarchive-post-title">${dog.dogName}</div>

          <div class="dogarchive-meta">
            <span class="dogarchive-type">${dog.dogBreed || ""}</span>

            <span class="dogarchive-writer">${dog.shelterName || ""}</span>

            <span class="dogarchive-date">
              등록 날짜 : ${dog.dogArchiveDate || ""}
            </span>
          </div>
        </div>
      </a>
    </div>
  `;
}

function renderDogCards() {
  if (!cardWrapper) return;

  if (!recommendedDogs || recommendedDogs.length === 0) {
    cardWrapper.innerHTML = `<div class="dogarchive-empty">추천 결과가 없습니다.</div>`;
    return;
  }

  cardWrapper.innerHTML = recommendedDogs.map((dog) => createDogCard(dog)).join("");
}

if (saveBtn) {
  saveBtn.addEventListener("click", () => {
    if (!isLoggedIn) {
      alert("로그인 후 결과를 저장할 수 있습니다.");
      location.href = `${contextPath}/user/login.us`;
      return;
    }

    location.href = `${contextPath}/matching/save.ma`;
  });
}

renderDogCards();