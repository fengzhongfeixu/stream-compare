package com.sugon.gsq.scs.config;

import com.sugon.gsq.scs.utils.Sha512Crypt;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义密码类--MD5
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(rawPassword.toString(), null);
    }
    /**
     * 密码匹配
     * @param rawPassword 用户输入密码
     * @param encodedPassword  数据库中密码
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
       /* Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.isPasswordValid(encodedPassword, rawPassword.toString(), null);*/

        /*使用openstack密码 加密解密*/
        return  Sha512Crypt.verifyPassword(rawPassword.toString(),encodedPassword);

    }
}
