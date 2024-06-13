package com.yanliang.algo.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author yanliang
 */
public class Test {

    public static void main(String[] args) {

        String base16String = "789CCB282929B0D2D7373432D033B434D1B334D33333B73233B030D04F2C29894FC92FCFCBC94F4CB12F4E2C4B8D2F482CC9B0F531CA284B0A0F2D8B34F22D8B8C0832F0A9B4A8F475F134F0A9F22CF7CD0A2CF175F12DF10DF12DF30D49AE008A9BFABA241BF93A9B18F855A51BFA85A41BFAE4396547A5DBDA2A3FDBDAFD62FDD467D3DA9FECDEA697929F5CA19C98920641CA46064626FA069640A4606160690000AE153831";

//        // 解码 Base16 字符串
//        byte[] decodedBytes = Base64.getDecoder().decode(base16String);
//
//        // 将字节数组转换为字符串
//        String decodedString = new String(decodedBytes);
//
//        // 输出解码后的字符串
//        System.out.println("Decoded string: " + decodedString);

        try {
            // 解码 Base16 字符串
            byte[] decodedBytes = Hex.decodeHex(base16String);

            // 将字节数组转换为字符串
            String decodedString = new String(decodedBytes);

            // 输出解码后的字符串
            System.out.println("Decoded string: " + decodedString);
        } catch (DecoderException e) {
            e.printStackTrace();
        }





    }





    public static String getMimeType(String fileName) {
        String mimeType = null;
        try {
            // 创建文件对象
            File file = new File(fileName);
            // 获取文件的扩展名
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            // 使用 Files.probeContentType 方法获取文件的 MIME 类型
            Path path = file.toPath();
            mimeType = Files.probeContentType(path);
            // 如果 probeContentType 方法未能检测到 MIME 类型，则手动映射文件扩展名到 MIME 类型
            if (mimeType == null) {
                mimeType = getMimeTypeForExtension(fileExtension);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mimeType;
    }

    // 根据文件扩展名获取对应的 MIME 类型
    public static String getMimeTypeForExtension(String fileExtension) {
        // 一般情况下，您可以根据文件扩展名手动映射到对应的 MIME 类型
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream"; // 默认 MIME 类型
        }
    }
}
