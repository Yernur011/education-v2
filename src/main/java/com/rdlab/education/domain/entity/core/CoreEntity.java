package com.rdlab.education.domain.entity.core;

import java.io.Serializable;

public interface CoreEntity<Id extends Serializable> extends Serializable {

    Id getId();

    void setId(Id id);
}
