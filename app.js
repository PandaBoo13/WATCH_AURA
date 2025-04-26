document.addEventListener("DOMContentLoaded", function () {
    function loadPage(page) {
        // Kiểm tra xem trang đã được tải chưa để tránh việc tải lại
        const content = document.getElementById("content");
        if (content.innerHTML.trim() === "") { // Nếu phần content chưa có nội dung
            fetch(`quanLyPPages/${page}.html`)
                .then(response => response.text())
                .then(html => {
                    content.innerHTML = html;
                })
                .catch(error => console.log("Lỗi tải trang:", error));
        }
    }

    // Tải trang mặc định (không cần dùng hash)
    loadPage("nguoiDung"); // Tải thẳng vào trang quản lý sinh viên
});

// Mảng các hình nền
const backgrounds = [
    'images/image1.jpg',
    'images/image2.jpg',
    'images/image3.jpg',
    // Thêm các hình nền khác nếu cần
];

// Đặt thời gian chuyển đổi (ví dụ: 5 giây)
let currentBackground = 0;

function changeBackground() {
    // Thay đổi hình nền
    document.body.style.backgroundImage = `url(${backgrounds[currentBackground]})`;
    
    // Cập nhật chỉ số hình nền tiếp theo
    currentBackground = (currentBackground + 1) % backgrounds.length; // Quay lại hình nền đầu tiên khi hết mảng
}

// Thay đổi hình nền mỗi 5 giây
setInterval(changeBackground, 5000);

