package bspkrs.treecapitator.compat;

import java.lang.reflect.Method;

import bspkrs.treecapitator.config.TCSettings;
import bspkrs.treecapitator.util.TCLog;
import cpw.mods.fml.common.Loader;

public class MultiMineCompat
{
    private static boolean isInitialized = false;
    
    public MultiMineCompat(String exclusionList)
    {
        if (Loader.isModLoaded(TCSettings.multiMineModID) && !isInitialized)
        {
            try
            {
                Class<?> mm = Class.forName("atomicstryker.multimine.common.MultiMine");
                Method instance = mm.getMethod("instance");
                Method setExcludedBlocksString = mm.getMethod("setExcludedBlocksString", String.class);
                Object mmInstanceObject = instance.invoke(null);
                setExcludedBlocksString.invoke(mmInstanceObject, exclusionList);
                
                isInitialized = true;
                TCLog.info("MultiMine compatibility initialized successfully.");
            }
            catch (Throwable e)
            {
                TCLog.severe("An error occurred while attempting to initialize MultiMine compatibility.");
                e.printStackTrace();
            }
        }
    }
}
