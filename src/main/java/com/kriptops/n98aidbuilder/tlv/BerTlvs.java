package com.kriptops.n98aidbuilder.tlv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BerTlvs {

    private final List<BerTlv> tlvs;

    public BerTlvs(BerTlv... aTlvs) {
        this(Arrays.asList(aTlvs));
    }

    public BerTlvs(List<BerTlv> aTlvs) {
        tlvs = aTlvs;
    }

    public BerTlv find(BerTag aTag) {
        for (BerTlv tlv : tlvs) {
            BerTlv found = tlv.find(aTag);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public String getHexValue(String hexName) {
        BerTlv tlv = this.find(new BerTag(hexName));
        if (tlv == null) {
            return null;
        }
        return tlv.getHexValue();
    }

    public List<BerTlv> findAll(BerTag aTag) {
        List<BerTlv> list = new ArrayList<>();
        for (BerTlv tlv : tlvs) {
            list.addAll(tlv.findAll(aTag));
        }
        return list;
    }

    public List<BerTlv> getList() {
        return tlvs;
    }
}
