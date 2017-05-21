package com.noryar.security.util.transformer;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import com.noryar.security.util.CamelCaseUtils;

/**
 * 功能描述：自定义hibernate查询结果转换器，将结果转换为指定类的集合,指定的类命名方式为驼峰命名.
 * @author Leon.
 */
public class CustomerAliasToBeanResultTransformer extends AliasedTupleSubsetResultTransformer {

    /**
     * .
     */
    private static final long serialVersionUID = 1L;
    /**
     * .
     */
    private static final int C_NUM_31 = 31;

    /**
     * .
     */
    private final Class<?> resultClass;
    /**
     * .
     */
    private boolean isInitialized;
    /**
     * .
     */
    private String[] aliases;
    /**
     * .
     */
    private Setter[] setters;

    /**
     * @param resultClassa resultClassa.
     */
    public CustomerAliasToBeanResultTransformer(final Class<?> resultClassa) {
        if (resultClassa == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        }
        isInitialized = false;
        this.resultClass = resultClassa;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean isTransformedValueATupleElement(final String[] aliasest, final int tupleLength) {
        return false;
    }

    /**
     * @param tuple tuple.
     * @param aliasestx aliasestx.
     * @return Object Object.
     */
    public final Object transformTuple(final Object[] tuple, final String[] aliasestx) {
        Object result;
        try {
            if (!isInitialized) {
                initialize(aliasestx);
            }
            result = resultClass.newInstance();
            for (int i = 0; i < this.aliases.length; i++) {
                if (setters[i] != null) {
                    setters[i].set(result, tuple[i], null);
                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }
        return result;
    }

    /**
     * @param aliasesx aliasesx.
     */
    private void initialize(final String[] aliasesx) {
        PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {
                PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
                PropertyAccessorFactory.getPropertyAccessor("field") });
        this.aliases = new String[aliasesx.length];
        setters = new Setter[aliasesx.length];
        for (int i = 0; i < aliasesx.length; i++) {
            String alias = aliasesx[i];
            if (alias != null) {
                alias = CamelCaseUtils.toCamelCase(alias);
                this.aliases[i] = alias;
                setters[i] = propertyAccessor.getSetter(resultClass, alias);
            }
        }
        isInitialized = true;
    }

    /**
     * @param o o.
     * @return boolean boolean.
     */
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerAliasToBeanResultTransformer that = (CustomerAliasToBeanResultTransformer) o;

        if (!resultClass.equals(that.resultClass)) {
            return false;
        }
        if (!Arrays.equals(aliases, that.aliases)) {
            return false;
        }

        return true;
    }

    /**
     * @return int int.
     */
    public final int hashCode() {
        int result = resultClass.hashCode();
        if (aliases != null) {
            int hcode = Arrays.hashCode(aliases);
            result = C_NUM_31 * result + hcode;
        } else {
            result = C_NUM_31 * result;
        }
        return result;
    }
}
