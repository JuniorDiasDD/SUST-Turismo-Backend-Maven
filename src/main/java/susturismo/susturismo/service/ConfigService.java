package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Formation;
import susturismo.susturismo.domain.Parceiro;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ConfigService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ParceiroService parceiroService;
    @Autowired
    FormationService formationService;

    public boolean gerarCategory(){
        categoryService.insert(new Category("Desporto","..."));
        categoryService.insert(new Category("Turismo","..."));
        categoryService.insert(new Category("Universidade","..."));
        categoryService.insert(new Category("Ambiente","..."));
        categoryService.insert(new Category("Temperatura","..."));
        categoryService.insert(new Category("Mundo","..."));
        categoryService.insert(new Category("Noticia","..."));
        return true;
    }
    public boolean gerarParceiro(){
        parceiroService.insert(new Parceiro("ISCEE",  "Universidade",   "https://www.susturismo.com/parceiros/ISCEE.png", "https://www.iscee.edu.cv/"));
        parceiroService.insert(new Parceiro("ESCP",  "ESCP Business School",   "https://www.susturismo.com/parceiros/ESCP.jpg", "https://escp.eu/"));
        parceiroService.insert(new Parceiro("iPVC",  "Instituto Politécnico de Viana do Castelo",   "https://www.susturismo.com/parceiros/IPVC.jpg", "https://www.ipvc.pt/"));
        parceiroService.insert(new Parceiro("I.S.P",  "I.S.P. LUSÍADA",   "https://www.susturismo.com/parceiros/ISPLH.jpg", "http://www.isplusiadabenguela.ed.ao/"));
        parceiroService.insert(new Parceiro("KOAN Consulting",  "KOAN Consulting",   "https://www.susturismo.com/parceiros/KOAN Consulting.png", "https://www.koanconsulting.com/"));
        parceiroService.insert(new Parceiro("Obreal Global",  "Obreal Global",   "https://www.susturismo.com/parceiros/Obreal Global.jpg", "https://obreal.org/"));
        parceiroService.insert(new Parceiro("UNINBE",  "Universidade do NAMIBE",   "https://www.susturismo.com/parceiros/UNIMBE.png", "https://www.uninbe.ao/"));
        parceiroService.insert(new Parceiro("UniPiaget de Cabo Verde",  "Universidade Jean Piaget",   "https://www.susturismo.com/parceiros/UniPiaget.png", "https://www.unipiaget.edu.cv/"));

        return true;
    }
    public boolean gerarFormation(){

        Optional<Category> category=categoryService.findByName("Universidade");
        Set<Category> categorySet=new HashSet<>();
        if(category.isPresent()) {
            categorySet.add(category.get());
        }
        formationService.insert(new Formation("Cooperação Universidade-Empresa e novos serviços universitários para orientação e tutoria estudantil:","Cooperação Universidade-Empresa e novos serviços universitários para orientação e tutoria estudantil, em colaboração com o setor empresarial: Propor e testar um modelo de colaboração estável entre Universidades e atores públicos e privados relacionados com o Turismo, especialmente tendo em conta a participação das comunidades locais nos territórios com maior potencial para a atividade turística.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=615","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Novas metodologias de formação prática e estudo de casos de sucesso de turismo sustentável","Novas metodologias de formação prática e estudo de casos de sucesso de turismo sustentável, com especial ênfase na abordagem do género e empoderamento das mulheres.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=614","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Curso de especialização:","Curso de especialização, numa base piloto, utilizando uma metodologia inovadora baseada em: Formação mista (online e presencial); Webinars especializados; Seminários internacionais; e Elaboração de trabalhos de conclusão de curso pelos alunos.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=613","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Educação empreendedora em Turismo Sustentável","Educação empreendedora em Turismo Sustentável: Conceber um Curso de Especialização modular, focado em Turismo Inovador Sustentável, que é ministrado pelas Universidades, mas suficientemente flexível para ser adaptado tanto aos estudantes (para a sua especialização e a melhoria da empregabilidade no setor) como a pessoas que já trabalham no setor de turismo (melhoria contínua de habilidades) e pessoas interessadas de instituições públicas e outros grupos de interesse. O curso deve necessariamente incluir novos paradigmas e conteúdos inovadores relacionados com o cuidado com o meio ambiente, digitalização e uso de tecnologias, proteção social e cultural das comunidades e identidades, gestão da diversidade, abordagem de género e inclusão.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=612","https://www.susturismo.com/img/sobrenos1.png",categorySet));

        return true;
    }

    private UUID gerarUUID(){
        return UUID.randomUUID();
    }
}
