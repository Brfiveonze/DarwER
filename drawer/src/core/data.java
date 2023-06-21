package core;

import java.io.File;

public class data {
    private String img_path = "./pic/";
    private String[] img_name = {"entity.png","attributes.png","relationship.png"};
    data(){}
    public String[] get_Img_name(){
        return img_name;
    }
    public String get_Img_path(){
        return img_path;
    }
}
