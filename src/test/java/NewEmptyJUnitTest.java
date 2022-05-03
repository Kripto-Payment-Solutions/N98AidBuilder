/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.kriptops.n98aidbuilder.N98AidBuilder;
import com.kriptops.n98aidbuilder.tlv.BerTag;
import com.kriptops.n98aidbuilder.tlv.BerTlv;
import com.kriptops.n98aidbuilder.tlv.BerTlvBuilder;
import com.kriptops.n98aidbuilder.tlv.BerTlvParser;
import com.kriptops.n98aidbuilder.tlv.HexUtil;

import java.util.ResourceBundle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }


    public void analize(String AID) {
        var bundle = ResourceBundle.getBundle("tagsofplatform");
        AID = AID.substring(4);
        var parser = new BerTlvParser();
        var parsedLvl1 = parser.parse(HexUtil.parseHex(AID));
        System.out.println(parsedLvl1);
        parsedLvl1.getList().forEach(tlv -> {
            var tagName = tlv.getTag().getBerTagHex();
            var tagDescription = bundle.containsKey(tagName) ? bundle.getString(tagName) : "";
            var tagValue = tlv.getHexValue();
            System.out.println(tagName + " (" + tagDescription + ") " + tagValue);
            if (tagName.equals("DF808002")) {
                var parsedLvl2 = parser.parse(HexUtil.parseHex(tagValue));
                parsedLvl2.getList().forEach(tlv2 -> {
                    var tagName2 = tlv2.getTag().getBerTagHex();
                    var tagDescription2 = bundle.containsKey(tagName2) ? bundle.getString(tagName2) : "";
                    var tagValue2 = tlv2.getHexValue();
                    System.out.println("\t" + tagName2 + " (" + tagDescription2 + ") " + tagValue2);
                });
            }
        });
    }

    @Test
    public void AMEXCTLS () {
        var aid = new N98AidBuilder().buildAID("04", "00", "A000000025", "01", "9F01061234567890129F090200019F160F3132333435363738393031323334359F150212349F4E0D54657374204D65726368616E749F1C0846726F6E743132339F1A0200009F1E083832313030303031DF808061010E9F3501219F3303E0F8C89F1B0400000000DF808020050000000000DF808021050000000000DF808022050000000000DF80802A06000099999900DF80802B06000000000000DF80802C06000000015000");
        System.out.println(aid);
        analize(aid);
    }

    
    @Test
    public void DINERSCTLS() {
    }
}
