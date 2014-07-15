using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(WA234.Startup))]
namespace WA234
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
