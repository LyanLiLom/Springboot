package com.example.crm04.Entity;

import jakarta.persistence.*;

/**
 * Để mapping khóa ngoại trong Entity
 * - Bước 1: Xem khóa chính 2 bảng đang quan hệ với nhau có phải là tự động tăng hay không.
 * Nếu tự động tăng thì không phải là OneToOne ==> OneToMany
 * - Bước 2: Nếu khóa chính không tự động tăng và vừa là khóa chính và khóa ngoại ==> OneToOne
 *
 * (*) OneToMany : Emtity nào giữ khóa ngoại thì sẽ có 2 Annotation sau đây
 * - @ManyToOne và @JoinColumn
 * Bảng được tham chiếu khóa ngoại sẽ map ngược lại
 * @
 */
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "phone")
    private String phone;

//    @Column(name = "role_id")
//    private String roleId;

    @ManyToOne
    @JoinColumn(name = "role_id")//tên cột khóa ngoại trong database dùng để liên kết dữ liệu
    private RolesEntity rolesEntity; // Dựa vào chữa đằng sau OneToMany hay ManyToOne thì sẽ biết được là một đối tượng hay một list đối tượng

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

//    public String getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(String roleId) {
//        this.roleId = roleId;
//    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }
}