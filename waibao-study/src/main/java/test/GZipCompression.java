package test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipCompression {
    private static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    //GZip解压缩
    public String uncompress(byte[] inputString){
        return gzipUnCompress(inputString, GZIP_ENCODE_UTF_8);
    }

    public static String gzipUnCompress(byte[] bytes, String encoding){
        if(bytes == null || bytes.length == 0){
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try{
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while((n = ungzip.read(buffer)) >= 0){
                out.write(buffer, 0, n);
            }
            return out.toString(GZIP_ENCODE_UTF_8);
        }catch (Exception e){
            throw new RuntimeException("GzipUnCompressError", e);
        }
    }

    //Gzip压缩
    public static byte[] compress(String original){
        return gzipCompress(original, GZIP_ENCODE_UTF_8);
    }

    public static byte[] gzipCompress(String str, String encoding){
        if(str == null || str.length() == 0){
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip ;
        try{
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        }catch (Exception e){
            throw new RuntimeException("GzipCompressError", e);
        }
        return out.toByteArray();
    }

}
