package shop.xianbao.modules.message.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class JPushParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appKey;

    private String masterSecret;
}
