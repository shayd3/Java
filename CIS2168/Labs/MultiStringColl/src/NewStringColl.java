/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ross
 */
public class NewStringColl extends MultiStringColl{
    
    public NewStringColl(){
        super();
    }
    
    public NewStringColl(int i){
        super(i);
    }
    
    @Override
    public void insert(String s) {
        if(super.belongs(s) == 0){
            super.insert(s);
        } 
    }
}
