package org.anne.common;

import java.util.HashMap;
import java.util.Map;

public enum Letter {
    A(0x19297A52, 0x8484B4FC),
    B(0x392E4A5C, 0),
    C(0x1928424C, 0),
    D(0x39294A5C, 0),
    E(0x3D0E421E, 0x8080FCFC),
    F(0x3D0E4210, 0x8080FC80),
    G(0x19285A4E, 0x8484FCFC),
    H(0x252F4A52, 0x8484FC84),
    I(0x1C42108E, 0),
    J(0x0C210A4C, 0),
    K(0x254C5292, 0xB0B0CCCC),
    L(0x2108421E, 0),
    M(0, 0),
    N(0, 0),
    O(0x19294A4C, 0),
    P(0x39297210, 0),
    Q(0, 0),
    R(0x39297292, 0x8C8CFC94),
    S(0x1D08305C, 0),
    T(0x1C421084, 0),
    U(0x25294A4C, 0),
    V(0, 0),
    W(0, 0),
    X(0, 0),
    Y(0x23151084, 0),
    Z(0x3C22221E, 0);


    private final int size5x6;
    private final int size8x10;

    private static final Map<Integer, Letter> map5x6 = new HashMap<>();
    private static final Map<Integer, Letter> map8x10 = new HashMap<>();

    static {
        for (Letter letter : Letter.values()) {
            map5x6.put(letter.size5x6, letter);
            map8x10.put(letter.size8x10, letter);
        }
    }

    public static Letter valueOf5x6(int size5x6) {
        return map5x6.get(size5x6);
    }

    public static Letter valueOf8x10(int size8x10) {
        return map8x10.get(size8x10);
    }

    Letter(int size5x6, int size8x10) {
        this.size5x6 = size5x6;
        this.size8x10 = size8x10;
    }
}
