package site.easy.to.build.crm.controller;

@Controller
public class ImportController {
    @Autowired
    private ImportService importService;

/*************  ✨ Codeium Command ⭐  *************/
    /**
     * This function imports data from a CSV file to the database.
     * @param file a CSV file containing data
     * @return a redirect to the home page
     */
/******  87b826b3-d179-46fd-9cea-803613dc7ab5  *******/    @RequestMapping("/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        importService.importData(file);
        return "redirect:/";
    }    
}
