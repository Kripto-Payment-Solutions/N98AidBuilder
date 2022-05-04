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
    public void amex_icc() {
        var aid = new N98AidBuilder().buildICCAID(
                "A000000025",
                "01",
                "9F090200019F160F3030303030303030303030303030309F150253119F4E0E43756C716920437573746F6D65729F1C085465726D696E616C9F1A0206049F3501219F3303E0F8C89F4005FF80F0A001DF80806106000000148000DF808020050000000000DF80802105C800000000DF80802205C8000000009F1B0400000000DF808026039F3704DF808027039F3704DF8081020100"
        );

//        var aid = new N98AidBuilder().buildAID(
//                "A000000025",
//                "01",
//                new BerTlv("9F09", "0001"),
//                new BerTlv("9F16", "303030303030303030303030303030"),
//                new BerTlv("9F15", "5311"),
//                new BerTlv("9F4E", "43756C716920437573746F6D6572"),
//                new BerTlv("9F1C", "5465726D696E616C"),
//                new BerTlv("9F1A", "0604"),
//                new BerTlv("9F35", "21"),
//                new BerTlv("9F33", "E0F8C8"),
//                new BerTlv("9F40", "FF80F0A001"),
//                new BerTlv("DF808061", "000000148000"),
//                new BerTlv("DF808020", "0000000000"),
//                new BerTlv("DF808021", "0000000000"),
//                new BerTlv("DF808022", "0000000000"),
//                new BerTlv("9F1B", "00000000"),
//                new BerTlv("DF808026", "9F3704"),
//                new BerTlv("DF808027", "9F3704"),
//                new BerTlv("DF808102", "00")
//        );
        System.out.println("AID AMEX CONTACT: ");
        System.out.println(aid);
        System.out.println("");
        System.out.println("Descripcion:");
        analize(aid);
        System.out.println("");
        System.out.println("");
    }
    
    @Test
    public void amex_nfc() {
        var aid = new N98AidBuilder().buildNFCAID(
                "04",
                "00",
                "A000000025",
                "01",
                "9F090200019F160F3030303030303030303030303030309F150253119F4E0E43756C716920437573746F6D65729F1C085465726D696E616C9F1A020604DF808061010E9F6E04000040009F3501219F3303E0F8C89F1B0400000000DF808020050000000000DF80802105C800000000DF80802205C800000000DF80802A06000099999900DF80802B06000000000000DF80802C06000000015000"
        );
        System.out.println("AID AMEX CONTACTLESS: ");
        System.out.println(aid);
        System.out.println("");
        System.out.println("Descripcion:");
        analize(aid);
        System.out.println("");
        System.out.println("");
    }
    

    @Test
    public void diners_icc() {
        System.out.println("AID DINERS CONTACT: ");
        var aid = new N98AidBuilder().buildICCAID("A0000001523010", "01", "9F090200019F160F3030303030303030303030303030309F150253119F4E0E43756C716920437573746F6D65729F1C085465726D696E616C9F1A0206049F3501219F3303E0F8C89F4005FF80F0A001DF80806106000000148000DF808020050000000000DF80802105C800000000DF80802205C8000000009F1B0400000000DF808026039F3704DF808027039F3704DF8081020100");
        System.out.println(aid);
        System.out.println("");
        System.out.println("Descripcion:");
        analize(aid);
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void amex_diners() {
        var aid = new N98AidBuilder().buildNFCAID(
                "06",
                "00",
                "A0000001523010",
                "01",
                "9F090200019F160F3030303030303030303030303030309F150253119F4E0E43756C716920437573746F6D65729F1C085465726D696E616C9F1A0206049F660404C00000DF80802801009F1B0400000000DF80802A06000099999900DF80802B06000000000000DF80802C060000000150009F3501219F3303E0F8C8"
        );
        System.out.println("AID DINERS CONTACTLESS: ");
        System.out.println(aid);
        System.out.println("");
        System.out.println("Descripcion:");
        analize(aid);
        System.out.println("");
        System.out.println("");
    }
    
}
