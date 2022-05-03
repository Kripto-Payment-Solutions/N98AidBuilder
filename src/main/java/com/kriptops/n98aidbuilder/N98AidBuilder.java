/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.kriptops.n98aidbuilder;

import com.kriptops.n98aidbuilder.tlv.BerTag;
import com.kriptops.n98aidbuilder.tlv.BerTlv;
import com.kriptops.n98aidbuilder.tlv.BerTlvBuilder;
import com.kriptops.n98aidbuilder.tlv.HexUtil;

/**
 *
 * @author carlos
 */
public class N98AidBuilder {

    /**
     * Construye AID para contactless
     * 
     * @param kty
     * @param tty
     * @param aid
     * @param asi
     * @param config
     * @return 
     */
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
    
    /**
     * Construye AID para contactless
     * 
     * @param kty
     * @param tty
     * @param aid
     * @param asi
     * @param config
     * @return 
     */
    public String buildAID(
            String kty,
            String tty,
            String aid,
            String asi,
            String config
    ) {
        var data = HexUtil.toHexString(
                new BerTlvBuilder(
                        new BerTlv("DF808000", kty),
                        new BerTlv("9C", tty),
                        new BerTlv("9F06", aid),
                        new BerTlv("DF808060", asi),
                        new BerTlv(
                                new BerTag("DF808002"),
                                HexUtil.parseHex(config)
                        )
                ).buildArray());

        var length = "0000" + Integer.toString(data.length() / 2, 16);
        length = length.substring(length.length() - 4);
        data = (length + data).toUpperCase();
        return data;
    }
    
    /**
     * Construye el AID para contact.
     * 
     * @param aid valor del aid a utilizar
     * @param asi indica si es match exacto o inexacto
     * @param config la configuracion del AID que se extrae del tool o una config manual
     * @return 
     */
    public String buildAID(
            String aid,
            String asi,
            BerTlv... config
    ) {
        var data = HexUtil.toHexString(
                new BerTlvBuilder(
                        new BerTlv("DF808000", "00"),
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
    
    /**
     * Construye el AID para contact.
     * 
     * @param aid valor del aid a utilizar
     * @param asi indica si es match exacto o inexacto
     * @param config la configuracion del AID que se extrae del tool o una config manual
     * @return 
     */
    public String buildAID(
            String aid,
            String asi,
            String config
    ) {
        var data = HexUtil.toHexString(
                new BerTlvBuilder(
                        new BerTlv("DF808000", "00"),
                        new BerTlv("9F06", aid),
                        new BerTlv("DF808060", asi),
                        new BerTlv(
                                new BerTag("DF808002"),
                                HexUtil.parseHex(config)
                        )
                ).buildArray());

        var length = "0000" + Integer.toString(data.length() / 2, 16);
        length = length.substring(length.length() - 4);
        data = (length + data).toUpperCase();
        return data;
    }
}
