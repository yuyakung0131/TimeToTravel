package com.emp.model;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class Blob {
	public static void main(String[] args) {
		String URL = "jdbc:mysql://localhost:3306/time_to_travel?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
		String USER = "root";
		String PASSWORD = "ggYY8877";
		
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			// 存圖片到資料庫
			String sql = "UPDATE member set MEMBER_IMG=? where MEMBER_ID = ?";
			try (PreparedStatement ps = connection.prepareStatement(sql);
				InputStream in = Files.newInputStream(Path.of("Kakashi.JPG"))) { 
				ps.setBinaryStream(1, in, in.available());
				ps.setInt(2, 6);
				int rowCount = ps.executeUpdate();
				System.out.println(rowCount + " row(s) inserted!!");
			}

//			// 讀取資料庫內的圖片
//			sql = "select isbn, book_name, price, author, publication_date, publisher_id, image "
//					+ "from book where isbn = ?;";
//			try (PreparedStatement ps = connection.prepareStatement(sql)) {
//				ps.setString(1, "9781617291999");
//				/*
//				 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
//				 * resources小括號內，參看ResultSet說明
//				 */
//				ResultSet rs = ps.executeQuery();
//				if (rs.next()) {
//					String isbn = rs.getString(1);
//					String bookName = rs.getString(2);
//					double price = rs.getDouble(3);
//					String author = rs.getString(4);
//					LocalDate publicationDate = rs.getObject(5, LocalDate.class);
//					String publisherId = rs.getString(6);
//					// 取得資料庫內的圖並存檔
//					InputStream in = rs.getBinaryStream(7);
//					
//					//將圖片複製輸出
//					Files.copy(in, Path.of("image2.jpg"), StandardCopyOption.REPLACE_EXISTING);
//					in.close();
//					System.out.println("Image saved...");
//					// 讀取一筆資料儲存至物件內
//					Book book = new Book(isbn, bookName, price, author, publicationDate, publisherId);
//					System.out.println(book.toString());
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}








