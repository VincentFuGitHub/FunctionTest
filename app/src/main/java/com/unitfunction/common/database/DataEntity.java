package com.unitfunction.common.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
import java.util.Date;

/**
 * Created by Vincent on 2018/9/13.
 */

@Entity(
        nameInDb = "DATA_TABLE"   // Table Name
)
public class DataEntity {

   @Id(autoincrement = true)    //this annotation makes the id auto increments, In Dao the long id works as the unique key in each table.
    private Long id;

    private String text;
    private String comment;
    private java.util.Date date;
   @Generated(hash = 1247285578)
   public DataEntity(Long id, String text, String comment, java.util.Date date) {
       this.id = id;
       this.text = text;
       this.comment = comment;
       this.date = date;
   }
   @Generated(hash = 1892108943)
   public DataEntity() {
   }
   public Long getId() {
       return this.id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public String getText() {
       return this.text;
   }
   public void setText(String text) {
       this.text = text;
   }
   public String getComment() {
       return this.comment;
   }
   public void setComment(String comment) {
       this.comment = comment;
   }
   public java.util.Date getDate() {
       return this.date;
   }
   public void setDate(java.util.Date date) {
       this.date = date;
   }

}
