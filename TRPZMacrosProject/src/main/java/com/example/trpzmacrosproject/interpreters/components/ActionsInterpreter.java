    package com.example.trpzmacrosproject.interpreters.components;

    import com.example.trpzmacrosproject.interpreters.exceptions.ActionsParsingException;
    import com.example.trpzmacrosproject.executor.TaskExecutor;
    import com.example.trpzmacrosproject.scripts.ScriptActions;
    import lombok.AllArgsConstructor;
    import org.json.JSONArray;
    import org.springframework.stereotype.Component;

    @Component
    @AllArgsConstructor
    public class ActionsInterpreter {
        TaskExecutor taskExecutor ;

        /**
         * Створення дій, та перевірка на валідність їх одразу
         * @param jsonArray дії у json -вигляді
         * @return ScriptActions об'єкт
         */

        public ScriptActions getScriptActions(JSONArray jsonArray) throws ActionsParsingException{
            try {
                // Створення об'єкту ScriptActions на основі JSON масиву
                ScriptActions scriptAction = new ScriptActions(jsonArray);
                // Виклик методу validate у TaskExecutor для перевірки валідності скрипту
                taskExecutor.validate(scriptAction);
                return scriptAction;
            }
            catch (Exception e)
            {
                throw new ActionsParsingException("Can't make ScriptActions: "+ e.getMessage(), e);
            }
        }

    }
