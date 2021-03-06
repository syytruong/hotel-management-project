/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author minhdao
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id")
    , @NamedQuery(name = "Task.findByName", query = "SELECT t FROM Task t WHERE t.name = :name")
    , @NamedQuery(name = "Task.findByCreationTime", query = "SELECT t FROM Task t WHERE t.creationTime = :creationTime")
    , @NamedQuery(name = "Task.findByCompletionTime", query = "SELECT t FROM Task t WHERE t.completionTime = :completionTime")
    , @NamedQuery(name = "Task.findByIsCancelled", query = "SELECT t FROM Task t WHERE t.isCancelled = :isCancelled")
    , @NamedQuery(name = "Task.findByIsUrgent", query = "SELECT t FROM Task t WHERE t.isUrgent = :isUrgent")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;
    
    @Column(name = "creationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Column(name = "completionTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionTime;

    @Column(name = "isCancelled")
    private boolean isCancelled;

    @Column(name = "isUrgent")
    private boolean isUrgent;
    
    @JoinColumn(name = "department", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Department department;
    
    @JoinColumn(name = "creationUser", referencedColumnName = "id")
    @ManyToOne
    private Employee creationUser;
    
    @JoinColumn(name = "completionUser", referencedColumnName = "id")
    @ManyToOne
    private Employee completionUser;
    
    @OneToMany(mappedBy = "task")
    private Collection<Attachment> attachments;

    public Task() {
    }

    public Task(int id) {
        this.id = id;
    }

    public Task(int id, String name, boolean isCancelled, boolean isUrgent) {
        this.id = id;
        this.name = name;
        this.isCancelled = isCancelled;
        this.isUrgent = isUrgent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Employee getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(Employee creationUser) {
        this.creationUser = creationUser;
    }

    public Employee getCompletionUser() {
        return completionUser;
    }

    public void setCompletionUser(Employee completionUser) {
        this.completionUser = completionUser;
    }

    @XmlTransient
    public Collection<Attachment> getAttachmentCollection() {
        return attachments;
    }

    public void setAttachmentCollection(Collection<Attachment> attachmentCollection) {
        this.attachments = attachmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id > 0 ? id : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id < 0 && other.id > 0) || (this.id > 0 && this.id == other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Task[ id=" + id + " ]";
    }
    
}