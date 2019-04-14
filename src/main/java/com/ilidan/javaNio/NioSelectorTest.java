package com.ilidan.javaNio;

import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

public class NioSelectorTest {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();//创建方式1
        Selector selector1 = SelectorProvider.provider().openSelector();//创建方式2
    }

}
