package susturismo.susturismo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import susturismo.susturismo.domain.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConfigService {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ParceiroService parceiroService;
    @Autowired
    FormationService formationService;
    @Autowired
    NoticiaService noticiaService;

    @Autowired
    EventService eventService;

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
        formationService.insert(new Formation("Cooperação Universidade-Empresa e novos serviços universitários para orientação e tutoria estudantil","Cooperação Universidade-Empresa e novos serviços universitários para orientação e tutoria estudantil, em colaboração com o setor empresarial: Propor e testar um modelo de colaboração estável entre Universidades e atores públicos e privados relacionados com o Turismo, especialmente tendo em conta a participação das comunidades locais nos territórios com maior potencial para a atividade turística.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=615","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Novas metodologias de formação prática e estudo de casos de sucesso de turismo sustentável","Novas metodologias de formação prática e estudo de casos de sucesso de turismo sustentável, com especial ênfase na abordagem do género e empoderamento das mulheres.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=614","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Curso de especialização","Curso de especialização, numa base piloto, utilizando uma metodologia inovadora baseada em: Formação mista (online e presencial); Webinars especializados; Seminários internacionais; e Elaboração de trabalhos de conclusão de curso pelos alunos.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=613","https://www.susturismo.com/img/sobrenos1.png",categorySet));
        formationService.insert(new Formation("Educação empreendedora em Turismo Sustentável","Educação empreendedora em Turismo Sustentável: Conceber um Curso de Especialização modular, focado em Turismo Inovador Sustentável, que é ministrado pelas Universidades, mas suficientemente flexível para ser adaptado tanto aos estudantes (para a sua especialização e a melhoria da empregabilidade no setor) como a pessoas que já trabalham no setor de turismo (melhoria contínua de habilidades) e pessoas interessadas de instituições públicas e outros grupos de interesse. O curso deve necessariamente incluir novos paradigmas e conteúdos inovadores relacionados com o cuidado com o meio ambiente, digitalização e uso de tecnologias, proteção social e cultural das comunidades e identidades, gestão da diversidade, abordagem de género e inclusão.","Estudantes","André Alves","http://portalacademico.homelinux.org:49152/moodle/course/view.php?id=612","https://www.susturismo.com/img/sobrenos1.png",categorySet));

        return true;
    }
    public boolean gerarEvents(){

        Optional<Category> category=categoryService.findByName("Universidade");
        Set<Category> categorySet=new HashSet<>();
        if(category.isPresent()) {
            categorySet.add(category.get());
        }

        LocalDateTime data = LocalDateTime.now();
        eventService.insert(new Event("Workshop","Workshop para as descubertas da filosofia moderna do sec 21", "Pedro Costa", data,data, "10:00", "12:30", "Universidade Jean Piaget", "Active","formação", "https://www.susturismo.com/img/sobrenos1.png", (float) 0, categorySet));
        eventService.insert(new Event("Palestra","Palestra abordando as polemicas sociais", "Antonia Semedo", data,data, "10:00", "12:30", "Universidade Jean Piaget", "Active","formação", "https://www.susturismo.com/img/sobrenos1.png", (float) 0, categorySet));
        eventService.insert(new Event("Intercambio","Intercambio internacional de alunos de varios paises da Europa com o principal objetivo de estreitar o relacionamento inter-escolar", "Antonio Borges", data,data, "8:00", "17:30", "Universidade Jean Piaget", "Active","formação", "https://www.susturismo.com/img/sobrenos1.png", (float) 0, categorySet));

        return true;
    }

    public boolean gerarNoticia(){

        Optional<Category> category=categoryService.findByName("Mundo");
        Set<Category> categorySet=new HashSet<>();
        if(category.isPresent()) {
            categorySet.add(category.get());
        }

        LocalDateTime data = LocalDateTime.now();

        noticiaService.insert(new Noticia("Governo de Cabo Verde realiza inspeção à Presidência após polémica", "noticia", "Em comunicado, o Governo cabo-verdiano avançou que o ministro das Finanças e do Fomento Empresarial, Olavo Correia, na qualidade de tutela de controlo, determinou uma ação de inspeção à Presidência da República, centrada nas rubricas de \"despesas com o pessoal\".\n" +
                "\n" +
                "Segundo a mesma fonte, a ação abarcará o período compreendido entre 9 de novembro de 2021, data da tomada de posse do Presidente da República, José Maria Neves, e a presente data, \"com o intuito de esclarecimento da legalidade e regularidade das despesas com o pessoal\".\n" +
                "\n" +
                "\"Nestes termos, nos próximos dias, uma equipa da Inspeção Geral das Finanças (IGF) estará na Presidência da República para a realização da mesma\", concluiu-se na nota do Governo, suportado pelo Movimento para a Democracia (MpD).\n" +
                "\n" +
                "A inspeção acontece após uma polémica gerada no país sobre um alegado salário de 300 mil escudos (2.720 euros) da primeira-dama, Débora Carvalho.\n" +
                "\n" +
                "O Presidente cabo-verdiano já tinha pedido um posicionamento do Tribunal de Contas e da Inspeção Geral das Finanças sobre a matéria,designadamente nas questões de legalidade, e anunciou que as regalias da companheira iam ser suspensas.\n" +
                "\n" +
                "José Maria Neves, antigo primeiro-ministro e eleito com o apoio do Partido Africano da Independência de Cabo Verde (PAICV, atualmente na oposição), anunciou ainda que, se for entendido que haverá algum montante a repor, \"será feito de imediato\".", "news", "https://www.susturismo.com/img/sobrenos1.png", data, "Active", categorySet) );
        noticiaService.insert(new Noticia("Preços dos combustíveis descem 4,49% em Cabo Verde", "noticia", " preço do gasóleo desce 4,95% para 123 escudos cabo-verdianos (cerca de 1,12 euros) o litro e a gasolina 4,28% para 132 escudos, de acordo com a nova tabela dos preços dos produtos petrolíferos regulados, já a vigorar.\n" +
                "\n" +
                "A ARME indicou ainda que o preço do gás butano é a excepção e sobe, com um aumento de 0,97%.\n" +
                "\n" +
                "Com isso, as garrafas de gás de 12,5 quilos passam a custar 1.814 escudos (cerca de 16,45 euros).\n" +
                "\n" +
                "Comparativamente ao período homólogo (janeiro de 2023), a variação média dos preços dos combustíveis corresponde a uma descida de 7,84%.", "combustivel", "https://www.susturismo.com/img/sobrenos1.png", data, "Active", categorySet) );

        noticiaService.insert(new Noticia("Cabo Verde. Hospital vai receber 27 mil euros/ano das obrigações verdes", "noticia", "nualmente será depositado na conta do Hospital Universitário Agostinho Neto (HUAN) um montante de cerca de 3,05 milhões de escudos (27,67 mil euros), o que perfaz cerca de 9,16 milhões de escudos (83,05 mil euros) tendo em conta que a maturidade do empréstimo é de três anos\", afirmou o presidente da Bolsa de Valores de Cabo Verde (BVC), Miguel Monteiro.\n" +
                "\n" +
                "Em novembro, o International Investment Bank Cabo Verde (iibCV, Banco Internacional de Investimentos) abriu o período de subscrição de obrigações verdes para apoiar projetos amigos do ambiente no principal hospital do país.\n" +
                "\n" +
                "A emissão destas obrigações verdes visa possibilitar o financiamento da atividade do iib, que inclui a execução da política de sustentabilidade e responsabilidade social, com uma remuneração adicional, correspondente a 0,5% ao ano sobre o montante total subscrito, a ser doada ao HUAN, na capital do país.\n" +
                "\n" +
                "O período de oferta decorreu de 24 de novembro a 27 de dezembro, o apuramento dos resultados com liquidação física e financeira ocorreu no dia seguinte e a cotação na BVC foi divulgada hoje, 29 de dezembro.\n" +
                "\n" +
                "Na apresentação dos resultados da emissão das primeiras obrigações verdes, na Praia, Miguel Monteiro explicou que a oferta pública de subscrição e admissão à cotação destas obrigações teve um montante inicial de 400 milhões de escudos (3,63 milhões de euros).\n" +
                "\n" +
                "Devido à \"grande procura\", o total subscrito foi de 610,47 milhões de escudos (5,54 milhões de euros), o que se traduz numa procura de 1,53 vezes superior à oferta inicial. \n" +
                "\n" +
                "Cerca de 35% do montante total subscrito foi realizado por não residentes.\n" +
                "\n" +
                "Foram submetidas um total de 79 ordens de subscrição válidas, sendo 63 subscritores de países da África, 13 da Europa e três dos Estados Unidos da América.\n" +
                "\n" +
                "Cerca de 27% do número dos subscritores é não-residente, valor superior ao alcançado na oferta pública de Blue Bonds [Obrigações Azuis] realizada no início do ano, que ficou em 21%.\n" +
                "\n" +
                "Os recursos serão utilizados pelo Hospital Agostinho Neto para o financiamento de projetos virados para a geração de energia renovável, eficiência energética, redução da poluição, gestão de águas e resíduos e na instalação de painéis solares.\n" +
                "\n" +
                "O objetivo inicial era atingir um montante global de 750 milhões de escudos (6,8 milhões de euros), onde o HUAN ia receber cerca de quatro milhões de escudos por ano (36 mil euros), o que totalizaria cerca de 12 milhões de escudos (108 mil euros) até o final da maturidade da operação, que é de três anos.\n" +
                "\n" +
                "O presidente da Comissão Executiva do iibCV, Francisco José Ferreira, disse que esta obrigação é \"mais uma peça no que têm sido os programas piloto do banco em tentar fazer-se transformar numa vertente de cultura interna em que a sustentabilidade e a responsabilidade social partem dos corações das pessoas e não de uma vertente escrita, metodológica ou de uma imposição regulatória à qual estão obrigados\". \n" +
                "\n" +
                "O banco apresenta-se como o terceiro maior de Cabo Verde, é liderado pelo grupo iib do Bahrain e participado em 10% pelo português Novo Banco.\n" +
                "\n" +
                "A última oferta pública de subscrição de obrigações na BVC ocorreu em janeiro de 2014, tendo como emitente a Imobiliária, Fundiária e Habitat (IFH, empresa estatal).", "Saude", "https://www.susturismo.com/img/sobrenos1.png", data, "Active", categorySet) );


        return true;
    }
    private UUID gerarUUID(){
        return UUID.randomUUID();
    }
}
