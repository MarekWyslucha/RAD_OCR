/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rad_ocr;

import java.io.File;
import model.CardData;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 *
 * @author Marek
 */
public class OCReader {

    private File imageFile;
    private String result;
    private ITesseract tesseract;
    private CardData cardData;

    public OCReader(File imageFile) {
        this.imageFile = imageFile;
        tesseract = new Tesseract1();
        
        File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        //C:\Users\[user]\AppData\Local\Temp\tess4j\tessdata
        tesseract.setDatapath(tessDataFolder.getParent());

        readText();
        cardData = new CardData(result);
    }

    public void readText() {
        try {
            result = tesseract.doOCR(this.imageFile);
        } catch (TesseractException te) {
            te.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }

    public CardData getCardData() {
        return cardData;
    }
}
