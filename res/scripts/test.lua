function update(c)
    print("INFO: Ran update in test.lua")
    local i = c:requestText("What will you do?")
    print("INFO: Input - " .. i)
    if (i == "quit") then
        return "quit"
    elseif (i == "help") then
        c:println("quit - quit the game")
    else
        c:println("I didn't understand you.")
    end
    c:println()
    return ""
end
