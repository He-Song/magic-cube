import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.POIDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

/**
 * @Author: hesong
 * @Describe:
 * @Date: 2019/8/30 15:36
 */
public class POITest {
    @Test
    public void test() throws Exception {
        String path = "城市体检各部门表20190412.doc";
        POIDocument doc;
        File runPath = new File(ResourceUtils.getURL("classpath:").getPath());
        String imagePathStr = runPath.getParentFile().getAbsolutePath() + "\\resources\\static\\resource_test\\image\\";
        String sourceFileName = runPath.getParentFile().getAbsolutePath()
                + "\\resources\\static\\resource_test\\城市体检各部门表20190412.doc";
        String targetFileName = runPath.getParentFile().getAbsolutePath()
                + "\\resources\\static\\resource_test\\城市体检各部门表20190412.html";
        File file = new File(imagePathStr);
        if (!file.exists()) {
            file.mkdirs();
        }
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFileName));
        org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
        // 保存图片，并返回图片的相对路径
        wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
            try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
                out.write(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "image/" + name;
        });
        wordToHtmlConverter.processDocument(wordDocument);
        org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(new File(targetFileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);

    }
}
