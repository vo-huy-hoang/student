package com.example.student.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
    // Phương thức này sẽ mã hóa mật khẩu và trả về chuỗi đã được mã hóa
    public static String hashPassword(String plainPassword) {
        // Độ mạnh của BCrypt có thể điều chỉnh bằng cách thay đổi giá trị nIterations
        int nIterations = 12;
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(nIterations));
        return hashedPassword;
    }

    // Phương thức này sẽ kiểm tra mật khẩu có khớp với mật khẩu đã được mã hóa hay không
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        System.out.println(hashPassword("VHH"));
        // $2a$12$Lkhmg.8lGInoZPPm5gnyCOrjxBJgz/pS2mvzGyE87x28Al5gfwiIa
        System.out.println(hashPassword("VHH").equals(hashPassword("VHH")));
        System.out.println(checkPassword("VHH", "$2a$12$Lkhmg.8lGInoZPPm5gnyCOrjxBJgz/pS2mvzGyE87x28Al5gfwiIa"));
    }
}
