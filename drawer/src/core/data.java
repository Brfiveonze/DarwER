package core;

import java.io.File;

public class data {
    private String img_path = "./pic/";
    private String[] img_name = new File(img_path).list();
    data(){}
    public String[] get_Img_name(){
        return img_name;
    }
    public String get_Img_path(){
        return img_path;
    }
}
