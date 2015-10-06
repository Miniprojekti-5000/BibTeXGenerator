package ohtu.miniprojekti5000.domain;

import ohtu.miniprojekti5000.logic.SpecialCharConverter;

public interface ReferenceInterface
{
    String getHeading();
    void setHeading(String heading);
    String toString(SpecialCharConverter specialCharConverter);
}
