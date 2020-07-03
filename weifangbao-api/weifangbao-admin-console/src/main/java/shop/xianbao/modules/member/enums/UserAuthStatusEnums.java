package shop.xianbao.modules.member.enums;

/**
 * @program: StoreCheckStatus
 * @description: 店铺审核状态枚举
 * @author: yh
 * @create: 2019-03-01 10:18
 **/
public enum UserAuthStatusEnums {

    Apply("申请中", 0),
    Checking("待审核", 1),
    Checked("审核通过",2),
    UnChecked("审核拒绝",3),
  ;

    private String name;

    private Integer key;

    UserAuthStatusEnums(String name, Integer key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }}


