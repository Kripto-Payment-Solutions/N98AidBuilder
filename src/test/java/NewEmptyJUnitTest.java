/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.kriptops.n98aidbuilder.tlv.BerTag;
import com.kriptops.n98aidbuilder.tlv.BerTlv;
import com.kriptops.n98aidbuilder.tlv.BerTlvBuilder;
import com.kriptops.n98aidbuilder.tlv.BerTlvParser;
import com.kriptops.n98aidbuilder.tlv.BerTlvs;
import com.kriptops.n98aidbuilder.tlv.HexUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        var bundle = ResourceBundle.getBundle("tagsofplatform");
        var AID = "00A6DF80800001009C01009F0607A0000000031010DF8080600101DF80800281879F01061234567890129F090200029F160F3132333435363738393031323334359F150212349F4E0D54657374204D65726368616E749F1C0846726F6E743132339F1A0206049F3501229F3303E0F8E89F4005F000F0F001DF80806106000000100000DF808020050000000000DF808021050000000000DF8080220500000000009F1B0400000000";
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
    public void aidsVisa() {
        System.out.println("Test AIDS Visa");
        for (var aid : new String[]{"A0000000031010", "A0000000032010", "A0000000033010"}) {
            var data = buildAID(
                    "00",
                    "00",
                    aid,
                    "01",
                    new BerTlv("9F01", "123456789012"),
                    new BerTlv("9F09", "0002"),
                    new BerTlv("9F16", "313233343536373839303132333435"),
                    new BerTlv("9F15", "1234"),
                    new BerTlv("9F4E", "54657374204D65726368616E74"),
                    new BerTlv("9F1C", "46726F6E74313233"),
                    new BerTlv("9F1A", "0604"),
                    new BerTlv("9F35", "21"),
                    new BerTlv("9F33", "6048C8"),
                    new BerTlv("9F40", "F000F02001"),
                    new BerTlv("DF808061", "000000100000"),
                    new BerTlv("DF808020", "0010000000"),
                    new BerTlv("DF808021", "DC4000A800"),
                    new BerTlv("DF808022", "DC4004F800"),
                    new BerTlv("DF808026", "9F0206"),
                    new BerTlv("DF808027", "9F3704"),
                    new BerTlv("9F1B", "00000000"),
                    new BerTlv("DF80802C", "000000015000")
            );
            System.out.println(data);
        }
    }

    public String buildAID(
            String kty,
            String tty,
            String aid,
            String asi,
            BerTlv... config
    ) {
        var data = HexUtil.toHexString(
                new BerTlvBuilder(
                        new BerTlv("DF808000", kty),
                        new BerTlv("9C", tty),
                        new BerTlv("9F06", aid),
                        new BerTlv("DF808060", asi),
                        new BerTlv(
                                new BerTag("DF808002"),
                                new BerTlvBuilder(config).buildArray()
                        )
                ).buildArray());

        var length = "0000" + Integer.toString(data.length() / 2, 16);
        length = length.substring(length.length() - 4);
        data = (length + data).toUpperCase();
        return data;
    }

}
