package com.shopr.domains.articles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.shopr.enums.NonFictionSubject;
import com.shopr.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("NONFICTION") //say what value to put in discr.column when adding row of this entity. d.type is STRING. can also write (value = "..")
@Entity //persistent layer and db layer, but no table because of the @inheritance(single table) on Book (?)
public class NonFiction extends Book{

    @Enumerated(EnumType.STRING) //values defined in enum, enum of type String
    private NonFictionSubject nonFictionSubject;


    public Type getType(){
        return Type.NONFICTION;
    }

}
