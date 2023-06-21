package core;

import java.io.File;

public class data {
    private String img_path = "./pic/object/";
    private String icon_path = "./pic/icon/";
    private String[] img_name = {"entity.png","attributes.png","relationship.png"};
    private String[] icon_name = {"icon_entity.png","icon_attributes.png","icon_relationship.png"};
    data(){}
    public String[] get_Img_name(){
        return img_name;
    }
    public String get_Img_path(){
        return img_path;
    }
    public String[] get_Icon_name(){
        return icon_name;
    }
    public String get_Icon_path(){
        return icon_path;
    }
}
