package com.ywt.laike.hotel.util;

import com.ywt.laike.hotel.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

/**
 * @author ywt
 */
public class PasswordUtils {
    /**
     * 算法名
     */
    private static String algorithmName = "md5";
    /**
     * 散列迭代次数
     */
    private static int hashIterations = 2;
    /**
     * 随机数生成器
     */
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public static final String mySalt = "ytw哈哈，。；134是fddfaassd-09342sd";

    /**
     * 给用户密码加密
     * @param user
     */
    public static void encryptPassword(User user) {
        // 给用户生成随机salt
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 给密码加密获得新密码
        String newPassword = new SimpleHash(algorithmName, user.getUserPwd(),
                ByteSource.Util.bytes(user.getSalt() + mySalt), hashIterations).toHex();
        user.setUserPwd(newPassword);
    }

    /**
     * 判断密码是否匹配
     * @param user
     * @param pwd
     * @return
     */
    public static boolean isMatchPassword(User user, String pwd) {
        if (user == null || StringUtils.isEmpty(user.getUserPwd())) {
            return false;
        }
        // 给密码加密获得新密码
        String newPassword = new SimpleHash(algorithmName, pwd,
                ByteSource.Util.bytes(user.getSalt() + mySalt), hashIterations).toHex();
        System.out.println("newPassword===" + newPassword);
        return user.getUserPwd().equals(newPassword);
    }
}
