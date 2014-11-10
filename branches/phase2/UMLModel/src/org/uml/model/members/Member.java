package org.uml.model.members;

import org.uml.model.ComponentBase;

/**
 * Represents  a class member.
 *
 * @author zoran
 * @see Literal
 * @see Field
 * @see Method
 * @see Constructor
 * @see ComponentBase
 *
 */
public class Member {

    private String name;
    
    /*
     * Modifier is a int value representing access and non-access modifier in
     * Java e.g. public is represented as 0x00000001, static as 0x00000008.
     * 
     * @see java.lang.reflect.Modifier
     */
    private int modifier;
    //private String modifiers; //modifiers are implemented as Strings, it is possible to later be changed to enum
    private ComponentBase declaringClass;
    protected Visibility visibility;

    /**
     * Default constructor. Only sets the name of a Member.
     *
     * @param name of a member
     */
    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public ComponentBase getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(ComponentBase declaringClass) {
        this.declaringClass = declaringClass;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}