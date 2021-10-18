
package trabajo02;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class download {
    private StreamedContent file;

    public download() {
        file = DefaultStreamedContent.builder()
                .name("LogoFarmavic.jpg")
                .contentType("image/jpg")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/image/LogoFarmavic.jpg"))
                .build();
    }

    public StreamedContent getFile() {
        return file;
    }
}
