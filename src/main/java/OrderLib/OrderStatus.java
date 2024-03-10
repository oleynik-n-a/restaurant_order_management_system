package OrderLib;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    Accepted,
    InProcess,
    Ready,
    Paid
}
