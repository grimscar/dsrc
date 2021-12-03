package script.working.vegvisir;

import script.obj_id;

public class vendor_buy_limit extends script.base_script
{
    public int OnAttach(obj_id self) throws InterruptedException
    {
        System.out.println("vendor_buy_limit script attached.");
        return SCRIPT_CONTINUE;
    }    
}

// getStringCRC
// getTemplateName