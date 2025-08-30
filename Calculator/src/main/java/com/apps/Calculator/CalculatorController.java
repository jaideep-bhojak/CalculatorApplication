package com.apps.Calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double a,
                            @RequestParam double num2,
                            @RequestParam(defaultValue = "0") double num3,
                            @RequestParam String operation,
                            Model model) {
        logger.info("Received calculation request: num1={}, num2={}, operation={}", a, num3, operation);
        double result = switch (operation) {
            case "add" -> a + num2 + num3;
            case "subtract" -> a - num2;
            case "multiply" -> a * num2;
            case "divide" -> num2 != 0 ? a / num2 : Double.NaN;
            default -> 0;
        };

        logger.debug("Performed addition: {} + {} = {}", a, num2, result);

        model.addAttribute("result", result);
        return "index";
    }
}
