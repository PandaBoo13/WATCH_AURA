// Lấy mã đơn hàng từ URL
const urlParams = new URLSearchParams(window.location.search);
const maDonHang = urlParams.get('ma_don_hang');

// Giả sử bạn có một hàm để lấy thông tin đơn hàng từ server
function fetchOrderDetails(maDonHang) {
    // Đây là ví dụ giả lập để lấy dữ liệu đơn hàng. Bạn sẽ thay thế bằng API hoặc cơ sở dữ liệu thực tế
    const orderDetails = {
        ma_don_hang: maDonHang,
        ma_nguoi_dung: "NG001",
        tong_tien: 1000000,
        trang_thai: "Chờ xác nhận",
        ngay_tao: "2025-04-01 10:00",
        chi_tiet: [
            { ma_san_pham: "SP001", ten_san_pham: "Đồng hồ Daniel Wellington", so_luong: 1, gia: 1000000 }
        ]
    };

    // Hiển thị thông tin đơn hàng
    const orderDetailsDiv = document.getElementById("orderDetails");
    orderDetailsDiv.innerHTML = `
        <h3>Mã đơn hàng: ${orderDetails.ma_don_hang}</h3>
        <p><strong>Mã người dùng:</strong> ${orderDetails.ma_nguoi_dung}</p>
        <p><strong>Tổng tiền:</strong> ${orderDetails.tong_tien}</p>
        <p><strong>Trạng thái:</strong> ${orderDetails.trang_thai}</p>
        <p><strong>Ngày tạo:</strong> ${orderDetails.ngay_tao}</p>

        <h4>Chi tiết sản phẩm:</h4>
        <table>
            <thead>
                <tr>
                    <th>Mã sản phẩm</th>
                    <th>Tên sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                </tr>
            </thead>
            <tbody>
                ${orderDetails.chi_tiet.map(item => `
                    <tr>
                        <td>${item.ma_san_pham}</td>
                        <td>${item.ten_san_pham}</td>
                        <td>${item.so_luong}</td>
                        <td>${item.gia}</td>
                    </tr>
                `).join('')}
            </tbody>
        </table>
    `;
}

// Gọi hàm để lấy chi tiết đơn hàng
fetchOrderDetails(maDonHang);
