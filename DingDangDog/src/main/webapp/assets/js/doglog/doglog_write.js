const form = document.getElementById("doglogWriteForm");
const imageUpload = document.getElementById("imageUpload");
const thumbnailPreview = document.getElementById("thumbnailPreview");
const writeContentEditor = document.getElementById("writeContentEditor");
const imgPlaceholder = document.querySelector(".img-placeholder");
const titleInput = document.querySelector(".write-title input");
const hiddenPostInput = document.getElementById("logPostHidden");

console.log("doglog_write.js 최신본 실행됨");

let uploadedImages = [];
let imageSequence = 0;

if (
  !form ||
  !imageUpload ||
  !thumbnailPreview ||
  !writeContentEditor ||
  !imgPlaceholder ||
  !titleInput ||
  !hiddenPostInput
) {
  throw new Error("필요한 요소를 찾을 수 없습니다.");
}

init();

function init() {
  bindEvents();
  syncEditorToHidden();
}

function bindEvents() {
  imageUpload.addEventListener("change", handleImageUpload);
  form.addEventListener("submit", handleSubmit);
  writeContentEditor.addEventListener("input", handleEditorInput);
  writeContentEditor.addEventListener("click", handleEditorClick);
  writeContentEditor.addEventListener("paste", handleEditorPaste);
  writeContentEditor.addEventListener("drop", handleEditorDrop);
  writeContentEditor.addEventListener("dragover", handleEditorDragOver);
}

function handleImageUpload(event) {
  const files = Array.from(event.target.files || []);
  const imageFiles = files.filter((file) => file.type.startsWith("image/"));

  if (!imageFiles.length) {
    alert("이미지 파일만 첨부할 수 있습니다.");
    imageUpload.value = "";
    syncInputFiles();
    return;
  }

  const newImages = imageFiles.map((file) => {
    const marker = `[[IMG_${imageSequence}]]`;
    imageSequence += 1;

    return {
      id: marker,
      marker: marker,
      file: file,
      previewUrl: URL.createObjectURL(file)
    };
  });

  // 핵심: 기존 이미지 유지 + 새 이미지 추가
  uploadedImages = [...uploadedImages, ...newImages];

  syncInputFiles();
  renderThumbnail();
  insertImagesToEditor(newImages);
  syncEditorToHidden();

  imageUpload.value = "";
}

function insertImagesToEditor(images) {
  images.forEach((image) => {
    const wrapper = createImageWrapper(image);
    const paragraph = document.createElement("p");
    paragraph.innerHTML = "<br>";

    writeContentEditor.appendChild(wrapper);
    writeContentEditor.appendChild(paragraph);
  });

  placeCaretToEnd(writeContentEditor);
}

function createImageWrapper(image) {
  const wrapper = document.createElement("div");
  wrapper.className = "editor-image-item";
  wrapper.setAttribute("contenteditable", "false");
  wrapper.setAttribute("data-marker", image.marker);

  const img = document.createElement("img");
  img.src = image.previewUrl;
  img.alt = "본문 이미지";
  img.setAttribute("data-marker", image.marker);
  img.setAttribute("data-image-type", "body-image");

  const removeButton = document.createElement("button");
  removeButton.type = "button";
  removeButton.className = "editor-image-remove";
  removeButton.setAttribute("data-marker", image.marker);
  removeButton.textContent = "×";

  wrapper.appendChild(img);
  wrapper.appendChild(removeButton);

  return wrapper;
}

function handleEditorClick(event) {
  const removeButton = event.target.closest(".editor-image-remove");

  if (!removeButton) return;

  const marker = removeButton.getAttribute("data-marker");
  removeImageByMarker(marker);
}

function removeImageByMarker(marker) {
  const targetImage = uploadedImages.find((image) => image.marker === marker);

  if (targetImage && targetImage.previewUrl) {
    URL.revokeObjectURL(targetImage.previewUrl);
  }

  uploadedImages = uploadedImages.filter((image) => image.marker !== marker);

  const wrapper = writeContentEditor.querySelector(
    `.editor-image-item[data-marker="${cssEscape(marker)}"]`
  );

  if (wrapper) {
    wrapper.remove();
  }

  syncInputFiles();
  renderThumbnail();
  syncEditorToHidden();
}

function syncInputFiles() {
  const dataTransfer = new DataTransfer();

  uploadedImages.forEach((image) => {
    dataTransfer.items.add(image.file);
  });

  imageUpload.files = dataTransfer.files;
}

function renderThumbnail() {
  if (!uploadedImages.length) {
    thumbnailPreview.src = "";
    thumbnailPreview.style.display = "none";
    imgPlaceholder.style.display = "block";
    return;
  }

  thumbnailPreview.src = uploadedImages[0].previewUrl;
  thumbnailPreview.style.display = "block";
  imgPlaceholder.style.display = "none";
}

function handleEditorInput() {
  syncEditorToHidden();
}

function handleEditorPaste(event) {
  const items = Array.from(event.clipboardData?.items || []);
  const hasImage = items.some((item) => item.type && item.type.startsWith("image/"));

  if (hasImage) {
    event.preventDefault();
    alert("본문에는 이미지 붙여넣기를 할 수 없습니다. 이미지 첨부 버튼을 사용해주세요.");
  }
}

function handleEditorDrop(event) {
  const files = Array.from(event.dataTransfer?.files || []);
  const hasImage = files.some((file) => file.type && file.type.startsWith("image/"));

  if (hasImage) {
    event.preventDefault();
    alert("본문에는 이미지를 직접 드래그할 수 없습니다. 이미지 첨부 버튼을 사용해주세요.");
  }
}

function handleEditorDragOver(event) {
  event.preventDefault();
}

function handleSubmit(event) {
  const titleValue = titleInput.value.trim();
  const contentText = getEditorPlainText().trim();
  
  normalizeNewImageMarkers();
  syncInputFiles();
  syncEditorToHidden();
  syncEditorToHidden();

  if (!uploadedImages.length) {
    event.preventDefault();
    alert("이미지는 최소 1장 이상 첨부해야 합니다.");
    return;
  }

  if (!titleValue) {
    event.preventDefault();
    alert("제목을 입력해주세요.");
    titleInput.focus();
    return;
  }

  if (!contentText) {
    event.preventDefault();
    alert("내용을 입력해주세요.");
    writeContentEditor.focus();
    return;
  }

  console.log("전송 직전 file 개수 =", imageUpload.files.length);

  const submitData = {
    title: titleValue,
    thumbnail: uploadedImages.length > 0 ? uploadedImages[0].marker : "",
    content: hiddenPostInput.value,
    imageCount: uploadedImages.length
  };

  console.log("등록 데이터", submitData);
}

function syncEditorToHidden() {
  hiddenPostInput.value = buildSubmitHtml().trim();
}

function buildSubmitHtml() {
  const nodes = Array.from(writeContentEditor.childNodes);
  const htmlParts = [];

  nodes.forEach((node) => {
    if (node.nodeType === Node.TEXT_NODE) {
      const text = node.textContent.trim();
      if (text) {
        htmlParts.push(`<p>${escapeHtml(text)}</p>`);
      }
      return;
    }

    if (node.nodeType !== Node.ELEMENT_NODE) return;

    if (node.classList.contains("editor-image-item")) {
      const marker = node.getAttribute("data-marker");
      if (marker) {
        htmlParts.push(`<p>${escapeHtml(marker)}</p>`);
      }
      return;
    }

    if (node.tagName === "DIV" || node.tagName === "P") {
      const inner = buildTextHtmlFromNode(node);
      if (inner !== "") {
        htmlParts.push(`<p>${inner}</p>`);
      }
      return;
    }

    const text = node.textContent.trim();
    if (text) {
      htmlParts.push(`<p>${escapeHtml(text)}</p>`);
    }
  });

  return htmlParts.join("");
}

function buildTextHtmlFromNode(parentNode) {
  const parts = [];

  Array.from(parentNode.childNodes).forEach((child) => {
    if (child.nodeType === Node.TEXT_NODE) {
      if (child.textContent) {
        parts.push(escapeHtml(child.textContent));
      }
      return;
    }

    if (child.nodeType !== Node.ELEMENT_NODE) return;

    if (child.classList.contains("editor-image-item")) {
      const marker = child.getAttribute("data-marker");
      if (marker) {
        parts.push(escapeHtml(marker));
      }
      return;
    }

    if (child.tagName === "BR") {
      return;
    }

    const text = child.textContent;
    if (text) {
      parts.push(escapeHtml(text));
    }
  });

  return parts.join("").trim();
}

function getEditorPlainText() {
  const clone = writeContentEditor.cloneNode(true);

  clone.querySelectorAll(".editor-image-item").forEach((wrapper) => {
    const spacer = document.createTextNode(" ");
    wrapper.replaceWith(spacer);
  });

  return clone.textContent.replace(/\u00A0/g, " ").trim();
}

function escapeHtml(value) {
  return value
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#39;");
}

function placeCaretToEnd(element) {
  element.focus();

  const selection = window.getSelection();
  const range = document.createRange();

  range.selectNodeContents(element);
  range.collapse(false);

  selection.removeAllRanges();
  selection.addRange(range);
}

function cssEscape(value) {
  if (window.CSS && typeof window.CSS.escape === "function") {
    return window.CSS.escape(value);
  }

  return value.replace(/[[\]"\\]/g, "\\$&");
}


function normalizeNewImageMarkers() {
  uploadedImages.forEach((image, index) => {
    const newMarker = `[[IMG_${index}]]`;
    const oldMarker = image.marker;

    if (oldMarker === newMarker) return;

    image.marker = newMarker;

    const wrapper = writeContentEditor.querySelector(
      `.editor-image-item[data-marker="${cssEscape(oldMarker)}"]`
    );

    if (wrapper) {
      wrapper.dataset.marker = newMarker;

      const img = wrapper.querySelector("img");
      if (img) {
        img.dataset.marker = newMarker;
      }

      const removeButton = wrapper.querySelector(".editor-image-remove");
      if (removeButton) {
        removeButton.dataset.marker = newMarker;
      }
    }
  });
}