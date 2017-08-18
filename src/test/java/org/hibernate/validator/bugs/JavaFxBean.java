package org.hibernate.validator.bugs;

import javafx.beans.property.ObjectProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class JavaFxBean {

    @NotNull
	@Min(value = 1L)
    @Max(value = 1L)
	private ObjectProperty<BigInteger> id;

	protected JavaFxBean() {
	}

    public BigInteger getId() {
        return id.get();
    }

    public ObjectProperty<BigInteger> idProperty() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id.set(id);
    }
}
