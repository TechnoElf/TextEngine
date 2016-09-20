function start(c)
    c:println("Started test.lua")
    c:println()
end

function update(c)
    local i = u:getInput(c, "What will you do?", " ")
    print("INFO: Input - " .. i[1])

    if (u:actionIs(i, "quit")) then
        return "quit"
    elseif (u:actionIs(i, "help")) then
        c:println("quit - quit the game")
    else
        c:println("I didn't understand you.")
    end

    c:println()

    print("INFO: Ran update in test.lua")
    return ""
end
